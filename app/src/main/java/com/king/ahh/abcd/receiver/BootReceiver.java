package com.king.ahh.abcd.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.king.ahh.abcd.service.JobService;
import com.king.ahh.abcd.service.MainService;
import com.king.ahh.abcd.service.RemoteService;
import com.king.ahh.abcd.util.ToastUtils;

public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "BootReceiver";
    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        startService(context);

        ToastUtils.toast(context, "开机自启动");

    }

    public void startService(Context context) {
        context.startService(new Intent(context, MainService.class));
        context.startService(new Intent(context, RemoteService.class));
        context.startService(new Intent(context, JobService.class));
    }
}
