package com.king.ahh.abcd.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.king.ahh.abcd.R;
import com.king.ahh.abcd.service.JobService;
import com.king.ahh.abcd.service.MainService;
import com.king.ahh.abcd.service.RemoteService;

import java.util.List;

/**
 * Created by gsc on 18-9-17.
 */

public class MainActivity extends Activity {
    private Button bt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
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

    public void startService() {
        startService(new Intent(this, MainService.class));
        startService(new Intent(this, RemoteService.class));
        startService(new Intent(this, JobService.class));
    }

    //　隐藏了APp
    private  void hideAppIcon() {
        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName(this, MainActivity.class);
        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }
}
