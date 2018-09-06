package ahmyth.mine.king.ahmyth.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ahmyth.mine.king.ahmyth.service.MainService;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        intent = new Intent( context, MainService.class );
        context.startService(intent);

    }
}
