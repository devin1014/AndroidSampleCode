package com.android.liuwei.myandroidcode.feature.ui.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.liuwei.myandroidcode.R;

import com.android.liuwei.myandroidcode.base.LogUtil;

/**
 * User: liuwei
 * Date: 2018-08-28
 * Time: 18:43
 */
public class TestFragment extends Fragment
{
    private String TAG = getClass().getSimpleName();

    @Override
    public void onAttach(Context context)
    {
        LogUtil.info(this, "onAttach");

        super.onAttach(context);
    }

    @Override
    public void onAttachFragment(Fragment childFragment)
    {
        LogUtil.info(this, "onAttachFragment");

        super.onAttachFragment(childFragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        LogUtil.info(this, "onCreate");

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        LogUtil.info(this, "onCreateView");

        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        LogUtil.info(this, "onViewCreated");

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        LogUtil.info(this, "onActivityCreated");

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart()
    {
        LogUtil.info(this, "onStart");

        super.onStart();
    }

    @Override
    public void onResume()
    {
        LogUtil.info(this, "onResume");

        super.onResume();
    }

    @Override
    public void onPause()
    {
        LogUtil.info(this, "onPause");

        super.onPause();
    }

    @Override
    public void onStop()
    {
        LogUtil.info(this, "onStop");

        super.onStop();
    }

    @Override
    public void onDestroyView()
    {
        LogUtil.info(this, "onDestroyView");

        super.onDestroyView();
    }

    @Override
    public void onDestroy()
    {
        LogUtil.info(this, "onDestroy");

        super.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        LogUtil.info(this, "onHiddenChanged:" + hidden);

        super.onHiddenChanged(hidden);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        LogUtil.info(this, "onConfigurationChanged");

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onDetach()
    {
        LogUtil.info(this, "onDetach");

        super.onDetach();
    }
}
