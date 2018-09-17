package com.king.ahh.abcd.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.king.ahh.abcd.activity.OnePixelActivity;
import com.king.ahh.abcd.manager.ConnectionManager;
import com.king.ahh.abcd.util.LogUtils;

public class MainService extends Service {
    private static Context contextOfApplication;
    private ExecutorService mExceutorService;

    private final IBinder mBinder = new MyBinder();
    private MyConn myConn = new MyConn();

    private ScreenReceiver mScreenReceiver;
    private static  final String TAG = "MainService";

    public MainService() {
    }

    @Override
    public void onCreate() {
        mExceutorService = Executors.newSingleThreadExecutor();
        mExceutorService.execute(new Runnable() {
            @Override
            public void run() {
                ConnectionManager.startAsync(MainService.this);
            }
        });

        //提升优先级
        if (mScreenReceiver == null) {
            mScreenReceiver = new ScreenReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            registerReceiver(mScreenReceiver, filter);
        }


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }


    @Override
    public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
        contextOfApplication = this;
        bindService(new Intent(this, RemoteService.class), myConn, Context.BIND_IMPORTANT);
        if (mExceutorService.isTerminated()) {
            mExceutorService.execute(new Runnable() {
                @Override
                public void run() {
                    ConnectionManager.startAsync(MainService.this);
                }
            });

        }
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mScreenReceiver != null)
            unregisterReceiver(mScreenReceiver);
            mScreenReceiver = null;
    }

    private void startAndBindService() {
        startService(new Intent(this, RemoteService.class));
        bindService(new Intent(this, RemoteService.class), myConn, Context.BIND_IMPORTANT);
    }

    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    public class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            startAndBindService();
        }
    }

    public class MyBinder extends Binder {

    }

    public class ScreenReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                LogUtils.log(TAG,"start OnePixelActivity");
                Intent it = new Intent(context, OnePixelActivity.class);
                context.startActivity(it);
            }
        }
    }


}
