package com.king.ahh.abcd.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.king.ahh.abcd.manager.ConnectionManager;

public class MainService extends Service {
    private static Context contextOfApplication;
    private ExecutorService mExceutorService;

    private final IBinder mBinder = new MyBinder();
    private MyConn myConn = new MyConn();
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


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }


    @Override
    public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
    {
        contextOfApplication = this;
        bindService(new Intent(this, RemoteService.class), myConn, Context.BIND_IMPORTANT);
        if(mExceutorService.isTerminated()) {
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
    }

    private void startAndBindService(){
        startService(new Intent(this, RemoteService.class));
        bindService(new Intent(this,RemoteService.class), myConn, Context.BIND_IMPORTANT);
    }

    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

    public class MyConn implements ServiceConnection{

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


}
