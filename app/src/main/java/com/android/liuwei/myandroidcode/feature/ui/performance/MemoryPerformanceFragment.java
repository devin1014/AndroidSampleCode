package com.android.liuwei.myandroidcode.feature.ui.performance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class MemoryPerformanceFragment extends BaseFragment
{
    static List<byte[]> sList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_memory_performance, container, false);
    }

    @OnClick(R.id.new_bytes)
    public void onBytesClick()
    {
        byte[] bytes = new byte[3 * 1024 * 1024]; //2M

        sList.add(bytes);
    }

    @OnClick(R.id.new_bitmap)
    public void onBitmapClick()
    {
        byte[] bytes = new byte[10 * 1024 * 1024]; //10M

        sList.add(bytes);
    }
}
