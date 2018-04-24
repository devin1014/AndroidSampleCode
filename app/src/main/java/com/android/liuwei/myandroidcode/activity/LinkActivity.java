package com.android.liuwei.myandroidcode.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.liuwei.myandroidcode.BaseActivity;
import com.android.liuwei.myandroidcode.R;

import butterknife.OnClick;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-04-24
 * Time: 17:25
 */
public class LinkActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_link;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initComponent();
    }

    private void initComponent()
    {
    }

    @OnClick(R.id.link_action)
    public void linkAction()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        //intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("nlnflgp://network"));
        startActivity(intent);
    }
}
