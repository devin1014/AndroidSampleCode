package com.android.liuwei.myandroidcode.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import liuwei.android.core.util.LogUtil;

/**
 * User: liuwei
 * Date: 2018-08-09
 * Time: 10:43
 */
public class MyBackgroundService extends Service
{
    private Binder mBinder = new Binder();

    @Override
    public void onCreate()
    {
        LogUtil.info(this, "onCreate");

        super.onCreate();

        // set service foreground,default is background.
        // startForeground(1, new Notification());
    }

    // START_NOT_STICKY:            service被进程杀死后不会自动启动（不会重新唤醒被杀死的进程）
    // START_STICKY:                service被进程杀死后会自动启动（重新唤醒被杀死的进程），onStartCommand一定被调用，Intent=NULL！
    // START_STICKY_COMPATIBILITY:  service被进程杀死后会自动启动（重新唤醒被杀死的进程），onStartCommand不保证一定调用，Intent=NULL！
    // START_REDELIVER_INTENT:      service被进程杀死后不会自动启动（不会重新唤醒被杀死的进程）,当进程被重新唤醒后，service会重新启动，onStartCommand一定被调用，Intent=之前的
    //
    // 该方法只有调用startService（intent）之后才会被调用
    // bindService不会调用这个方法！！！
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        LogUtil.info(this, "onStartCommand: " + intent);

        return START_STICKY;
    }

    // service在一个生命周期内，onBing/onUnbind只会被调用一次
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        LogUtil.info(this, "onBind: " + intent);

        return mBinder;
    }

    // super.onUnbind方法返回false！onRebind方法不会被调用！！！
    @Override
    public boolean onUnbind(Intent intent)
    {
        LogUtil.info(this, "onUnbind: " + intent);

        return true;
    }

    // onUnbind必须返回true，才会调用！！！
    @Override
    public void onRebind(Intent intent)
    {
        LogUtil.info(this, "onRebind: " + intent);

        super.onRebind(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        LogUtil.info(this, "onConfigurationChanged: " + newConfig);

        super.onConfigurationChanged(newConfig);
    }

    // startService（Intent） -> stopService(Intent) ,会调用
    // bindService（Intent） -> unbindService(Intent) ,会调用
    // startService -> bindService -> unBindService -> stopService ,会调用
    // startService -> bindService -> stopService -> unBindService ,会调用
    // bindService -> startService -> stopService -> unBindService ,会调用
    // bindService -> startService -> unBindService -> stopService ,会调用
    @Override
    public void onDestroy()
    {
        LogUtil.warn(this, "onDestroy");

        super.onDestroy();
    }

    @Override
    public void onLowMemory()
    {
        LogUtil.warn(this, "onLowMemory");

        super.onLowMemory();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent)
    {
        LogUtil.warn(this, "onTaskRemoved: " + rootIntent);

        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onTrimMemory(int level)
    {
        LogUtil.warn(this, "onTrimMemory: " + level);

        super.onTrimMemory(level);
    }

}
