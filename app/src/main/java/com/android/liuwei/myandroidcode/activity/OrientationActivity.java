package com.android.liuwei.myandroidcode.activity;

import android.content.Context;
import android.content.Intent;

import com.android.liuwei.myandroidcode.BaseActivity;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-04-17
 * Time: 10:40
 */
public class OrientationActivity extends BaseActivity
{
    public static void startActivity(Context context)
    {
        context.startActivity(new Intent(context, OrientationActivity.class));
    }
}
