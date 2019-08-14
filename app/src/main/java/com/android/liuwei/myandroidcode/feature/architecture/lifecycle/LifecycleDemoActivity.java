package com.android.liuwei.myandroidcode.feature.architecture.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.feature.architecture.lifecycle.IPresenter.MyPresenter;

public class LifecycleDemoActivity extends AppCompatActivity
{
    private MyPresenter mMyPresenter = new MyPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        getLifecycle().addObserver(mMyPresenter);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        getLifecycle().removeObserver(mMyPresenter);
    }
}
