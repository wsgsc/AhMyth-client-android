package com.king.ahh.abcc.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.king.ahh.abcc.util.LogUtils;

/**
 * Created by gsc on 18-9-18.
 */

public class OnePixelActivity extends Activity {
    private static final String TAG = "OnePixel";
    private ScreenOffReceiver mScreenOffReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.log(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;
        window.setAttributes(params);


        if (mScreenOffReceiver == null) {
            mScreenOffReceiver = new ScreenOffReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_ON);
            registerReceiver(mScreenOffReceiver, filter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mScreenOffReceiver != null)
            unregisterReceiver(mScreenOffReceiver);
            mScreenOffReceiver = null;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    class ScreenOffReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                LogUtils.log(TAG,"fininsh OnePixelActivity");
                OnePixelActivity.this.finish();
            }
        }
    }

}
