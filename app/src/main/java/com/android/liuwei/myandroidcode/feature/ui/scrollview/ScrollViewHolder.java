package com.android.liuwei.myandroidcode.feature.ui.scrollview;

import android.content.Intent;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;

import butterknife.OnClick;

public class ScrollViewHolder extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_holder_scrollview;
    }

    @OnClick(R.id.sv_scrollview_recyclerview)
    void onScrollViewRecyclerViewClick()
    {
        startActivity(new Intent(this, ScrollViewRecyclerViewActivity.class));
    }

}
