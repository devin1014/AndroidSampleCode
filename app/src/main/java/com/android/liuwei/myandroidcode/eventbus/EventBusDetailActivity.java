package com.android.liuwei.myandroidcode.eventbus;

import android.widget.Toast;

import com.android.liuwei.myandroidcode.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import liuwei.android.core.base.BaseActivity;

/**
 * User: liuwei
 * Date: 2018-10-16
 * Time: 10:53
 */
public class EventBusDetailActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_eventbus;
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop()
    {
        EventBus.getDefault().unregister(this);

        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventReceiver(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
