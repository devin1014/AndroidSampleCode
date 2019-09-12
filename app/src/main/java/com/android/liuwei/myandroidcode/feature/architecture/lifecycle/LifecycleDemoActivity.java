package com.android.liuwei.myandroidcode.feature.architecture.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;
import com.android.liuwei.myandroidcode.feature.architecture.lifecycle.MyLifecycleObserver.DefaultObserver;

public class LifecycleDemoActivity extends BaseActivity
{
    private DefaultObserver mObserver = new DefaultObserver();
    private DefaultObserver mObserverStart = new DefaultObserver();
    private DefaultObserver mObserverResume = new DefaultObserver();

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_lifecycle;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(mObserver);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        getLifecycle().removeObserver(mObserver);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        getLifecycle().addObserver(mObserverStart);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        getLifecycle().removeObserver(mObserverStart);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        getLifecycle().addObserver(mObserverResume);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        getLifecycle().removeObserver(mObserverResume);
    }
}
