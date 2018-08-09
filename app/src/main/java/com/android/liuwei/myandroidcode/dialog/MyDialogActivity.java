package com.android.liuwei.myandroidcode.dialog;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.deviceinfo.DeviceInfoActivity;

import butterknife.OnClick;
import liuwei.android.core.base.BaseActivity;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-08
 * Time: 16:19
 */
public class MyDialogActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_mydialog;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.show_dialog)
    public void showDialog()
    {
        AlertDialog.Builder builder = new Builder(this);
        builder.setTitle("Title")
                .setMessage("Description")
                .setPositiveButton("OK", new OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        openActivity();
                    }
                })
                .show();
    }

    @OnClick(R.id.open_activity)
    public void openActivity()
    {
        startActivity(new Intent(this, DeviceInfoActivity.class));
    }
}
