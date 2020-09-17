package com.android.liuwei.myandroidcode.feature.ui.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;

public class IntentDataActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_intent_data;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TextView textView = findViewById(R.id.data_text);

        textView.setText(getIntent().toString());
    }
}
