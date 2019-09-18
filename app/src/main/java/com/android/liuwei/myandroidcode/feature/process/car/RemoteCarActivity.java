package com.android.liuwei.myandroidcode.feature.process.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;

public class RemoteCarActivity extends BaseActivity
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

        Object obj = getIntent().getParcelableExtra("extra_data");

        if (obj != null)
        {
            ((TextView) findViewById(R.id.extra_info)).setText(obj.toString());
        }

        Intent result = new Intent();
        result.putExtra("resultData", "success");
        result.putExtra("resultData2", 2);
        setResult(Activity.RESULT_OK, result);
    }
}
