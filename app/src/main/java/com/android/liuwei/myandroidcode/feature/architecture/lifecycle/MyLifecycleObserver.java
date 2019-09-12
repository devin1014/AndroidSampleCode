package com.android.liuwei.myandroidcode.feature.architecture.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.android.liuwei.myandroidcode.base.LogUtil;

public interface MyLifecycleObserver extends LifecycleObserver
{
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner);

    @OnLifecycleEvent(Event.ON_START)
    void onStart(LifecycleOwner owner);

    @OnLifecycleEvent(Event.ON_STOP)
    void onStop(LifecycleOwner owner);

    @OnLifecycleEvent(Event.ON_RESUME)
    void onResume(LifecycleOwner owner);

    @OnLifecycleEvent(Event.ON_PAUSE)
    void onPause(LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event);

    class DefaultObserver implements MyLifecycleObserver
    {
        @Override
        public void onCreate(LifecycleOwner owner)
        {
            LogUtil.info(this, "onCreate");
        }

        @Override
        public void onDestroy(LifecycleOwner owner)
        {
            LogUtil.info(this, "onDestroy");
        }

        @Override
        public void onStart(LifecycleOwner owner)
        {
            LogUtil.info(this, "onStart");
        }

        @Override
        public void onStop(LifecycleOwner owner)
        {
            LogUtil.info(this, "onStop");
        }

        @Override
        public void onResume(LifecycleOwner owner)
        {
            LogUtil.info(this, "onResume");
        }

        @Override
        public void onPause(LifecycleOwner owner)
        {
            LogUtil.info(this, "onPause");
        }

        @Override
        public void onLifecycleChanged(LifecycleOwner owner, Event event)
        {
            LogUtil.info(this, "onLifecycleChanged:" + event);
        }
    }
}
