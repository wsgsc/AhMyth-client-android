package com.king.ahh.abcd.app;

import android.app.Application;

/**
 * Created by gsc on 18-9-18.
 */

public class KingApplication extends Application {
    private static KingApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized KingApplication getInstance() {
        return instance;
    }
}
