package com.android.liuwei.myandroidcode.feature.ui.scrollview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;
import com.android.liuwei.myandroidcode.base.ListProvider;
import com.android.liuwei.myandroidcode.base.LogUtil;
import com.android.liuwei.myandroidcode.base.adapter.ListAdapter;

import butterknife.BindView;

public class ScrollViewRecyclerViewActivity extends BaseActivity
{
    private static final int SIZE = 30;

    @BindView(R.id.scroll_view)
    NestedScrollView mScrollView;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_scrollview_recyclerview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new ListAdapter(this, ListProvider.newInstance(SIZE), R.layout.comp_nav_list));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        LogUtil.log(this, "mScrollView: height=" + mScrollView.getHeight());
        LogUtil.log(this, "mRecyclerView: height=" + mRecyclerView.getHeight());
    }
}
