package ahmyth.mine.king.ahmyth.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

import ahmyth.mine.king.ahmyth.util.ServiceUtils;
import ahmyth.mine.king.ahmyth.util.ToastUtils;

/**
 * Created by gsc on 18-9-9.
 */

public class RemoteService extends Service{

    private final IBinder mBinder = new MyBinder();
    private MyConn myConn = new MyConn();


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(this,MainService.class), myConn, Context.BIND_IMPORTANT);
        return Service.START_STICKY;
    }

    @Override
    public void onCreate() {

    }

    public class MyBinder extends Binder {

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

    private void startAndBindService(){
        startService(new Intent(this, MainService.class));
        bindService(new Intent(this,MainService.class), myConn, Context.BIND_IMPORTANT);
        ToastUtils.toast(this, "开启MainService");
    }
}
