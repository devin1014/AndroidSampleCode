package com.android.liuwei.myandroidcode.feature.ui.performance;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;

import butterknife.OnClick;

public class PerformanceActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_performance;
    }

    @OnClick(R.id.performance_layout)
    public void onPerformanceLayoutClick()
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LayoutPerformanceFragment())
                .commit();
    }

    @OnClick(R.id.performance_memory)
    public void onPerformanceMemoryClick()
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MemoryPerformanceFragment())
                .commit();
    }

    @OnClick(R.id.performance_network)
    public void onPerformanceNetworkClick()
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new NetworkPerformanceFragment())
                .commit();
    }
}
