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

    @OnClick(R.id.link_channel_1)
    public void link2Channel1()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("cntvlink://channel?id=1"));
        startActivity(intent);
    }

    @OnClick(R.id.link_channel_2)
    public void link2Channel2()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("cntvlink://channel?id=2"));
        startActivity(intent);
    }

    @OnClick(R.id.link_schedule)
    public void link2Schedule()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("cntvlink://schedule"));
        startActivity(intent);
    }
}
