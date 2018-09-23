package com.king.ahh.abcc.app;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by gsc on 18-9-19.
 */

public class KingApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化 jg sdk
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);

    }
}
