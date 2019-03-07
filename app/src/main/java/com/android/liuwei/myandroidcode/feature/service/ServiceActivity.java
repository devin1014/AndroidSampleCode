package com.android.liuwei.myandroidcode.feature.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.android.liuwei.myandroidcode.R;

import butterknife.OnClick;
import com.android.liuwei.myandroidcode.base.BaseActivity;
import com.android.liuwei.myandroidcode.base.LogUtil;

/**
 * User: liuwei
 * Date: 2018-08-09
 * Time: 11:35
 */
public class ServiceActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_service;
    }

    @OnClick(R.id.service_start_service)
    public void startService()
    {
        startService(new Intent(this, MyBackgroundService.class));

        startService(new Intent(this, MyBackgroundIntentService.class));
    }

    @OnClick(R.id.service_stop_service)
    public void stopService()
    {
        stopService(new Intent(this, MyBackgroundService.class));

        stopService(new Intent(this, MyBackgroundIntentService.class));
    }

    @OnClick(R.id.service_bind_service)
    public void bindService()
    {
        if (mServiceConnection == null)
        {
            mServiceConnection = new MyServiceConnection();
        }

        bindService(new Intent(this, MyBackgroundService.class), mServiceConnection, Service.BIND_AUTO_CREATE);
    }

    @OnClick(R.id.service_unbind_service)
    public void unbindService()
    {
        if (mServiceConnection != null)
        {
            unbindService(mServiceConnection);

            mServiceConnection = null;
        }
    }

    @OnClick(R.id.service_new_activity)
    public void toNewServiceActivity()
    {
        startActivity(new Intent(this, ServiceActivity2.class));
    }

    private MyServiceConnection mServiceConnection;

    private class MyServiceConnection implements ServiceConnection
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            LogUtil.info(this, "onServiceConnected: " + name.getShortClassName());
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            LogUtil.warn(this, "onServiceDisconnected: " + name.getShortClassName());
        }
    }
}
