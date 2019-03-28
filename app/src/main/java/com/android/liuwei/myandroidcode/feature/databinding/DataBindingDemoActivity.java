package com.android.liuwei.myandroidcode.feature.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;
import com.android.liuwei.myandroidcode.databinding.ActivityDatabindingBinding;

public class DataBindingDemoActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_databinding;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean bindingView()
    {
        ActivityDatabindingBinding viewBinding = DataBindingUtil.setContentView(this, getActivityLayout());

        viewBinding.setViewModel(new DataSample());

        return true;
    }
}
