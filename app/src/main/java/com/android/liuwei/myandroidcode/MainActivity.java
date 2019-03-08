package com.android.liuwei.myandroidcode;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.android.liuwei.myandroidcode.base.BaseActivity;

public class MainActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }
}
