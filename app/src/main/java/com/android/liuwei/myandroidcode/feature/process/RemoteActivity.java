package com.android.liuwei.myandroidcode.feature.process;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;

public class RemoteActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_remote;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((TextView) findViewById(R.id.title)).setText(RemoteData.getData());

        ((TextView) findViewById(R.id.extra_info)).setText(getIntent().getStringExtra("extra_data"));

        RemoteData.setData("RemoteActivity");
    }
}
