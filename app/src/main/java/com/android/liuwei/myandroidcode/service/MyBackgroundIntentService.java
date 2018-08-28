package com.android.liuwei.myandroidcode.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.support.annotation.Nullable;

import liuwei.android.core.util.LogUtil;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-08-09
 * Time: 18:22
 */
public class MyBackgroundIntentService extends IntentService
{
    public MyBackgroundIntentService()
    {
        super("MyBackgroundIntentService");
    }

    private int index = 0;

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        LogUtil.info(this, String.format("onHandleIntent: %s %s", index, intent));

        index++;

        //LogUtil.info(this, "CurrentThread:" + Thread.currentThread().getName());

        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        LogUtil.info(this, "onHandleIntent complete!");
    }

    @Override
    public void onCreate()
    {
        LogUtil.info(this, "onCreate");

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        LogUtil.info(this, "onStartCommand: " + intent);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        LogUtil.info(this, "onBind: " + intent);

        return null;
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        LogUtil.info(this, "onUnbind: " + intent);

        return true;
    }

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
