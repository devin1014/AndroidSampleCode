package com.android.liuwei.myandroidcode.feature.ui.performance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseFragment;

public class LayoutPerformanceFragment extends BaseFragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_layout_performance, container, false);
    }
}
