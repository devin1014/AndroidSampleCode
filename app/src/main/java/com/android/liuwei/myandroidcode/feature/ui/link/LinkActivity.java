package com.android.liuwei.myandroidcode.feature.ui.link;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.liuwei.myandroidcode.R;

import butterknife.OnClick;
import com.android.liuwei.myandroidcode.base.BaseActivity;

/**
 * User: liuwei
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000)
        {

        }
    }

    @OnClick(R.id.link_browser)
    public void link2Browser()
    {
        Intent intent = new Intent();
        intent.setData(Uri.parse("http://www.baidu.com"));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        startActivityForResult(intent, 1000);
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

    @OnClick(R.id.link_activation)
    public void link2Activation()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("cntvlink://account/tcl_activation?url=https://testevent.api.my7v.com/fifa/#/CardInput"));
        startActivity(intent);
    }
}
