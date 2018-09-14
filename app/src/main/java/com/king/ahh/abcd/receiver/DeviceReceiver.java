package com.king.ahh.abcd.receiver;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by gsc on 18-9-7.
 */

public class DeviceReceiver extends android.app.admin.DeviceAdminReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        toast(context, "Enabled");
        super.onEnabled(context, intent);
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        toast(context, "Disabled");
        super.onDisabled(context, intent);
    }

    private void toast(Context context,String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
