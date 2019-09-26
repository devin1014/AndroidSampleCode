package com.android.liuwei.myandroidcode.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * User: liuwei
 * Date: 2018-04-16
 * Time: 18:26
 */
public abstract class BaseActivity extends AppCompatActivity
{
    private Unbinder mButterKnife;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        LogUtil.log(this, "onCreate:" + (savedInstanceState != null ? savedInstanceState : ""));

        super.onCreate(savedInstanceState);

        if (!bindingView())
        {
            setContentView(getActivityLayout());
        }

        mButterKnife = ButterKnife.bind(this);
    }

    protected boolean bindingView()
    {
        return false;
    }

    protected abstract int getActivityLayout();

    @Override
    protected void onStart()
    {
        LogUtil.log(this, "onStart");

        super.onStart();
    }

    @Override
    protected void onRestart()
    {
        LogUtil.log(this, "onRestart");

        super.onRestart();
    }

    @Override
    protected void onResume()
    {
        LogUtil.log(this, "onResume");

        super.onResume();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        LogUtil.log(this, "onRestoreInstanceState:" + (savedInstanceState != null ? savedInstanceState : ""));

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        LogUtil.log(this, "onSaveInstanceState:" + (outState != null ? outState : ""));

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
    {
        LogUtil.log(this, "onSaveInstanceState:" + (outState != null ? outState : "") + "," + (outPersistentState != null ? outPersistentState : ""));

        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onPause()
    {
        LogUtil.log(this, "onPause");

        super.onPause();
    }

    @Override
    protected void onStop()
    {
        LogUtil.log(this, "onStop");

        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        LogUtil.log(this, "onDestroy");

        if (mButterKnife != null)
        {
            mButterKnife.unbind();
        }

        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        LogUtil.info(this, "onConfigurationChanged:" + newConfig);

        super.onConfigurationChanged(newConfig);
    }
}
