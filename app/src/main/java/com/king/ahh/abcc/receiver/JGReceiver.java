package com.king.ahh.abcc.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.king.ahh.abcc.service.JobService;
import com.king.ahh.abcc.service.MainService;
import com.king.ahh.abcc.service.RemoteService;
import com.king.ahh.abcc.util.ServiceUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by gsc on 18-9-22.
 */

public class JGReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ServiceUtils.startAllService(context);
    }
}
