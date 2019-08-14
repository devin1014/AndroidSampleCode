package com.android.liuwei.myandroidcode.feature.ui.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;
import com.android.liuwei.myandroidcode.databinding.ActivityDatabindingBinding;

import java.util.Timer;
import java.util.TimerTask;

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
        final DataSample dataSample = new DataSample();

        ActivityDatabindingBinding viewBinding = DataBindingUtil.setContentView(this, getActivityLayout());

        viewBinding.setViewModel(dataSample);

        SwitchCompat switchCompat = viewBinding.getRoot().findViewById(R.id.switcher);

        switchCompat.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                dataSample.setSwitch(isChecked);
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                dataSample.setSwitch(false);
            }
        }, 5, 5 * 1000);

        return true;
    }
}
