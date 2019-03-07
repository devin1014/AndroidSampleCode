package com.android.liuwei.myandroidcode.feature.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.liuwei.myandroidcode.R;

import com.android.liuwei.myandroidcode.base.BaseActivity;

/**
 * User: liuwei
 * Date: 2018-08-28
 * Time: 18:40
 */
public class FragmentActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}
