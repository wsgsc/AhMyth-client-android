package com.king.ahh.abcc.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.king.ahh.abcc.util.ServiceUtils;
import com.king.ahh.abcc.util.ToastUtils;

/**
 * Created by gsc on 18-9-7.
 */

public class JobService extends android.app.job.JobService{
    private static final String TAG = "JobService";
    private int jobId = 0;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        scheduleJob(getJobInfo());
        return START_NOT_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        startService();
        scheduleJob(getJobInfo());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {

        return false;
    }

    private JobInfo getJobInfo() {
        JobInfo.Builder builder = new JobInfo.Builder(jobId, new ComponentName(this, JobService.class));
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPersisted(true);
        builder.setRequiresCharging(false);
        builder.setRequiresDeviceIdle(false);

        // 7.0 以上　时间至少是　15min 循环时间
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMinimumLatency(5 * 1000) ;//设置最小执行间隔
            builder.setOverrideDeadline(10 * 1000);//设置任务执行的最晚延迟时间

//            builder.setMinimumLatency(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS); //执行的最小延迟时间
//            builder.setOverrideDeadline(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);  //执行的最长延时时间
//            builder.setMinimumLatency(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);
//            builder.setBackoffCriteria(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS, JobInfo.BACKOFF_POLICY_LINEAR);//线性重试方案

        } else {
            builder.setPeriodic(3000);
        }

        return builder.build();
    }

    private void scheduleJob(JobInfo jobInfo) {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int ret = jobScheduler.schedule(jobInfo);
        if(ret <= 0)
            ToastUtils.toast(this, "scheduleJob error");
        else {
            //ToastUtils.toast(this, "scheduleJob success :" + ret);
            //LogUtils.log(TAG,"scheduleJob success :" + ret);
        }
    }

    private void startService() {
        boolean isMainServiceWork = ServiceUtils.isServiceRunning(this, ServiceUtils.MAINSERVICE);
        boolean isRemoteServiceWork = ServiceUtils.isServiceRunning(this, ServiceUtils.REMOTESERVICE);

        if(!isMainServiceWork)
            startService(new Intent(this, MainService.class));
        if(!isRemoteServiceWork)
            startService(new Intent(this,RemoteService.class));

        //LogUtils.log(TAG," start Job Service ");
       // ToastUtils.toast(this, "start Job Service");
    }

}
