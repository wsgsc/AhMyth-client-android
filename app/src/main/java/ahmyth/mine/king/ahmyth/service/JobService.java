package ahmyth.mine.king.ahmyth.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import ahmyth.mine.king.ahmyth.util.ServiceUtils;
import ahmyth.mine.king.ahmyth.util.ToastUtils;

/**
 * Created by gsc on 18-9-7.
 */

public class JobService extends android.app.job.JobService{
    private static final String SERVICE_MAIN = "ahmyth.mine.king.ahmyth.service.MainService";
    private static final String SERVICE_REMOTE = "ahmyth.mine.king.ahmyth.service.RemoteService";
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
        jobFinished(params, false);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        scheduleJob(getJobInfo());
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
            builder.setMinimumLatency(10 * 1000) ;//设置最小执行间隔
            builder.setOverrideDeadline(60 * 1000);//设置任务执行的最晚延迟时间
        } else {
            builder.setPeriodic(3000);
        }

        return builder.build();
    }

    private void scheduleJob(JobInfo jobInfo) {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if(jobScheduler.schedule(jobInfo) <=0)
            ToastUtils.toast(this, "scheduleJob error");
        else
            ToastUtils.toast(this, "scheduleJob success");
    }

    private void startService() {
        boolean isMainServiceWork = ServiceUtils.isServiceRunning(this, SERVICE_MAIN);
        boolean isRemoteServiceWork = ServiceUtils.isServiceRunning(this, SERVICE_REMOTE);
        if(!isMainServiceWork)
            startService(new Intent(this, MainService.class));
        if(!isRemoteServiceWork)
            startService(new Intent(this,RemoteService.class));

        ToastUtils.toast(this, "startService");
    }

}
