package com.android.liuwei.myandroidcode.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.liuwei.myandroidcode.MyEventBusIndex;
import com.android.liuwei.myandroidcode.R;

import org.greenrobot.eventbus.EventBus;

import liuwei.android.core.base.BaseActivity;

/**
 * User: liuwei
 * Date: 2018-10-16
 * Time: 10:50
 */
public class EventBusDemoActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_eventbus;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        EventBus
                .builder()
                .addIndex(new MyEventBusIndex())
                .installDefaultEventBus();
    }

    private Handler mHandler = new Handler();

    public void postMessage(View view)
    {
        mHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                EventBus.getDefault().post("EventBus Message Posted!");
            }
        }, 3000);

        startActivity(new Intent(this, EventBusDetailActivity.class));
    }
}
