package com.android.liuwei.myandroidcode.service;

import android.content.Intent;

import com.android.liuwei.myandroidcode.R;

import butterknife.OnClick;

/**
 * User: liuwei(wei.liu@neulion.com.com)
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