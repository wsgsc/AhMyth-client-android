package com.king.ahh.abcc.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import com.king.ahh.abcc.service.JobService;
import com.king.ahh.abcc.service.MainService;
import com.king.ahh.abcc.service.RemoteService;

import java.util.ArrayList;

/**
 * Created by gsc on 18-9-9.
 */

public class ServiceUtils {
    public static final String JOBSERVICE = "com.king.ahh.abcc.service.JobService";
    public static final String MAINSERVICE = "com.king.ahh.abcc.service.MainService";
    public static final String REMOTESERVICE = "com.king.ahh.abcc.service.RemoteService";


    public static boolean isServiceRunning(Context context, String ServiceName) {
        if (("").equals(ServiceName) || ServiceName == null)
            return false;
        ActivityManager myManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager
                .getRunningServices(Integer.MAX_VALUE);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString()
                    .equals(ServiceName)) {
                return true;
            }
        }
        return false;
    }
    
    public static void startAllService(Context context) {
        if(!isServiceRunning(context, MAINSERVICE))
            context.startService(new Intent(context, MainService.class));
        if(!isServiceRunning(context, REMOTESERVICE))
            context.startService(new Intent(context, RemoteService.class));
        if(!isServiceRunning(context, JOBSERVICE))
            context.startService(new Intent(context, JobService.class));
    }

}
