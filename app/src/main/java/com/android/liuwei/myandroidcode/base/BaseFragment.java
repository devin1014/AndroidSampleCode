package com.android.liuwei.myandroidcode.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * User: liuwei
 * Date: 2018-04-16
 * Time: 18:17
 */
public abstract class BaseFragment extends Fragment
{
    private Unbinder mButterKnife;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mButterKnife = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView()
    {
        mButterKnife.unbind();

        super.onDestroyView();
    }
}
