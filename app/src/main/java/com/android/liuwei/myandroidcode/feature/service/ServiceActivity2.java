package com.android.liuwei.myandroidcode.feature.service;

import android.content.Intent;

import com.android.liuwei.myandroidcode.R;

import butterknife.OnClick;

/**
 * User: liuwei
 * Date: 2018-08-09
 * Time: 11:40
 */
public class ServiceActivity2 extends ServiceActivity
{
    @OnClick(R.id.service_new_activity)
    public void toNewServiceActivity()
    {
        startActivity(new Intent(this, ServiceActivity.class));
    }
}