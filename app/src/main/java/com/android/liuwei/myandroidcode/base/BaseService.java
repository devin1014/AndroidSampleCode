package com.android.liuwei.myandroidcode.base;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;

abstract public class BaseService extends Service
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        LogUtil.info(this, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        LogUtil.info(this, String.format("onStartCommand(%s , %s)", flags, startId));

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        LogUtil.info(this, "onBind");

        return null;
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        LogUtil.info(this, "onUnbind");

        return super.onUnbind(intent);
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags)
    {
        LogUtil.info(this, String.format("bindService(%s)", flags));

        return super.bindService(service, conn, flags);
    }

    @Override
    public void unbindService(ServiceConnection conn)
    {
        LogUtil.info(this, String.format("unbindService()"));

        super.unbindService(conn);
    }

    @Override
    public void onDestroy()
    {
        LogUtil.info(this, String.format("onDestroy(%s)"));

        super.onDestroy();
    }
}
