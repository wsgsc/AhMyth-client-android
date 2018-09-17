package com.king.ahh.abcd.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.joker.annotation.PermissionsNonRationale;
import com.joker.annotation.PermissionsRequestSync;
import com.joker.api.Permissions4M;
import com.joker.api.wrapper.Wrapper;
import com.king.ahh.abcd.R;
import com.king.ahh.abcd.receiver.DeviceReceiver;
import com.king.ahh.abcd.util.ToastUtils;

import java.util.List;

import static com.king.ahh.abcd.activity.SettingsActivity.CAMERA_CODE;
import static com.king.ahh.abcd.activity.SettingsActivity.LOCATION_CODE;
import static com.king.ahh.abcd.activity.SettingsActivity.RECORD_CODE;
import static com.king.ahh.abcd.activity.SettingsActivity.SMS_CODE;


@PermissionsRequestSync(
        permission = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_SMS,Manifest.permission.CAMERA},
        value = {RECORD_CODE,
                LOCATION_CODE,
                SMS_CODE,
                CAMERA_CODE})
public class SettingsActivity extends Activity implements  View.OnClickListener {
    public static final int RECORD_CODE =  5;
    public static final int LOCATION_CODE = 6;
    public static final int SMS_CODE = 7;
    public static final int CAMERA_CODE = 8;
    private Button bt_showapp,bt_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();
        //finish();
    }

    public void init() {
        initView();
        registerDeviceManager();
    }

    public void initView() {
        bt_showapp = (Button) findViewById(R.id.bt_showapp);
        bt_check = (Button)  findViewById(R.id.bt_check);
        bt_showapp.setOnClickListener(this);
        bt_check.setOnClickListener(this);
    }


    public void registerDeviceManager() {
        DevicePolicyManager dPManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName componentName =new ComponentName(this,DeviceReceiver.class);

        //如果没有注册设备管理器
        if(!dPManager.isAdminActive(componentName)) {
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "");
            startActivityForResult(intent, 0);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(String permission) {
        return super.shouldShowRequestPermissionRationale(permission);
    }

    private void requestPermission() {
        Permissions4M.get(this)
                .requestPermissions(Manifest.permission.RECORD_AUDIO,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.READ_SMS
                    ,Manifest.permission.CAMERA)
                .requestCodes(RECORD_CODE, LOCATION_CODE, SMS_CODE, CAMERA_CODE)
                .requestListener(new Wrapper.PermissionRequestListener() {
                    @Override
                    public void permissionGranted(int code) {
                        switch(code) {
                            case RECORD_CODE:
                                ToastUtils.toast(SettingsActivity.this, "录音权限申请成功");
                                break;
                            case LOCATION_CODE:
                                ToastUtils.toast(SettingsActivity.this, "定位权限申请成功");
                                break;
                            case SMS_CODE:
                                ToastUtils.toast(SettingsActivity.this, "短信权限申请成功");
                                break;
                            case CAMERA_CODE:
                                ToastUtils.toast(SettingsActivity.this, "相机权限申请成功");
                                break;
                            default:
                                break;
                        }
                    }
                    @Override
                    public void permissionDenied(int code) {
                        switch(code) {
                            case RECORD_CODE:
                                ToastUtils.toast(SettingsActivity.this, "录音权限申请失败");
                                break;
                            case LOCATION_CODE:
                                ToastUtils.toast(SettingsActivity.this, "定位权限申请失败");
                                break;
                            case SMS_CODE:
                                ToastUtils.toast(SettingsActivity.this, "短信权限申请失败");
                                break;
                            case CAMERA_CODE:
                                ToastUtils.toast(SettingsActivity.this, "相机权限申请失败");
                                break;
                            default:
                                break;
                        }

                    }
                    @Override
                    public void permissionRationale(int code) {
                        switch(code) {
                            case RECORD_CODE:
                                ToastUtils.toast(SettingsActivity.this, "请开启录音权限");
                                break;
                            case LOCATION_CODE:
                                ToastUtils.toast(SettingsActivity.this, "请开启定位权限");
                                break;
                            case SMS_CODE:
                                ToastUtils.toast(SettingsActivity.this, "请开启短信权限");
                                break;
                            case CAMERA_CODE:
                                ToastUtils.toast(SettingsActivity.this, "请开启相机权限");
                                break;
                            default:
                                break;
                        }

                    }
                })
                .requestCustomRationaleListener(new Wrapper.PermissionCustomRationaleListener() {
                    @Override
                    public void permissionCustomRationale(int code) {
                        switch(code) {
                            case RECORD_CODE:
                                ToastUtils.toast(SettingsActivity.this, "custom 请开启录音权限");
                                new AlertDialog.Builder(SettingsActivity.this)
                                        .setMessage("录音权限申请：\n我们需要您开启读取录音权限(in activity with annotation)")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Permissions4M.get(SettingsActivity.this)
                                                        // 注意添加 .requestOnRationale()
                                                        .requestOnRationale()
                                                        .requestPermissions(Manifest.permission.RECORD_AUDIO)
                                                        .requestCodes(RECORD_CODE)
                                                        .request();
                                            }
                                        })
                                        .show();
                                break;
                            case LOCATION_CODE:
                                ToastUtils.toast(SettingsActivity.this, "custom 请开启定位权限");
                                new AlertDialog.Builder(SettingsActivity.this)
                                        .setMessage("读取地理位置权限申请：\n我们需要您开启读取地理位置权限(in activity with annotation)")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Permissions4M.get(SettingsActivity.this)
                                                        // 注意添加 .requestOnRationale()
                                                        .requestOnRationale()
                                                        .requestPermissions(Manifest.permission.ACCESS_COARSE_LOCATION)
                                                        .requestCodes(LOCATION_CODE)
                                                        .request();
                                            }
                                        })
                                        .show();
                                break;
                            case SMS_CODE:
                                new AlertDialog.Builder(SettingsActivity.this)
                                        .setMessage("读取短信权限申请：\n我们需要您开启读取短信权限(in activity with annotation)")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Permissions4M.get(SettingsActivity.this)
                                                        // 注意添加 .requestOnRationale()
                                                        .requestOnRationale()
                                                        .requestPermissions(Manifest.permission.READ_SMS)
                                                        .requestCodes(SMS_CODE)
                                                        .request();
                                            }
                                        })
                                        .show();
                                break;
                            case CAMERA_CODE:
                                new AlertDialog.Builder(SettingsActivity.this)
                                        .setMessage("相机权限申请：\n我们需要您相机权限(in activity with annotation)")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Permissions4M.get(SettingsActivity.this)
                                                        // 注意添加 .requestOnRationale()
                                                        .requestOnRationale()
                                                        .requestPermissions(Manifest.permission.CAMERA)
                                                        .requestCodes(CAMERA_CODE)
                                                        .request();
                                            }
                                        })
                                        .show();
                                break;
                            default:
                                break;
                        }
                    }
                }).request();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bt_check:
                requestPermission();
                break;
            case R.id.bt_showapp:
                showAppIcon();
                break;
            default:
                break;
        }
    }

    @PermissionsNonRationale({RECORD_CODE, LOCATION_CODE, SMS_CODE})
    public void nonRationale(int code, Intent intent) {
        startActivity(intent);
    }

    //　隐藏APp
    private  void showAppIcon() {
        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName(this, MainActivity.class);
        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        ToastUtils.toast(this, "showAppIcon");
    }
}
