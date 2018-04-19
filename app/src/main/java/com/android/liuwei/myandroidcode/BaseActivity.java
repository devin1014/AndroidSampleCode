package com.android.liuwei.myandroidcode;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-04-16
 * Time: 18:26
 */
public abstract class BaseActivity extends AppCompatActivity
{
    private String TAG = getClass().getSimpleName();
    private Unbinder mButterKnife;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        LogUtil.log(TAG, "onCreate:" + savedInstanceState);

        super.onCreate(savedInstanceState);

        mButterKnife = ButterKnife.bind(this);
    }

    @Override
    protected void onStart()
    {
        LogUtil.log(TAG, "onStart");

        super.onStart();
    }

    @Override
    protected void onRestart()
    {
        LogUtil.log(TAG, "onRestart");

        super.onRestart();
    }

    @Override
    protected void onResume()
    {
        LogUtil.log(TAG, "onResume");

        LogUtil.log(TAG, "---------------------------------->");

        super.onResume();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        LogUtil.log(TAG, "onRestoreInstanceState:" + savedInstanceState);

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        LogUtil.log(TAG, "onSaveInstanceState:" + outState);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause()
    {
        LogUtil.log(TAG, "onPause");

        super.onPause();
    }

    @Override
    protected void onStop()
    {
        LogUtil.log(TAG, "onStop");

        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        LogUtil.log(TAG, "onDestroy");

        mButterKnife.unbind();

        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        LogUtil.logInfo(TAG, "onConfigurationChanged:" + newConfig);

        super.onConfigurationChanged(newConfig);
    }
}
