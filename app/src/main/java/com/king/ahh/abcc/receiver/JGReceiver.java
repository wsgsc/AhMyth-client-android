package com.king.ahh.abcc.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.king.ahh.abcc.service.JobService;
import com.king.ahh.abcc.service.MainService;
import com.king.ahh.abcc.service.RemoteService;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by gsc on 18-9-22.
 */

public class JGReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();


        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {



            Toast.makeText(context,"ahmyth UJPush 用户注册成功",Toast.LENGTH_SHORT).show();

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {

            Toast.makeText(context,"ahmyth U接受到推送下来的自定义消息",Toast.LENGTH_SHORT).show();

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {


            Toast.makeText(context,"ahmyth 接受到推送下来的通知" ,Toast.LENGTH_SHORT).show();

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {



            Toast.makeText(context,"ahmyth 用户点击打开了通知",Toast.LENGTH_SHORT).show();

        } else {


            Toast.makeText(context,"ahmyth Unhandled intent - " + intent.getAction(),Toast.LENGTH_SHORT).show();
        }


        startService(context);
    }

    public void startService(Context context) {
        context.startService(new Intent(context, MainService.class));
        context.startService(new Intent(context, RemoteService.class));
        context.startService(new Intent(context, JobService.class));
    }
}
