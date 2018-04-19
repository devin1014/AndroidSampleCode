package com.android.liuwei.myandroidcode;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-04-16
 * Time: 18:17
 */
public abstract class BaseFragment extends Fragment
{
    private final String TAG = getClass().getSimpleName();

    private Unbinder mButterKnife;

    public BaseFragment()
    {
        LogUtil.log(TAG, "new " + getClass().getSimpleName() + "()");
    }

    @Override
    public void onAttach(Context context)
    {
        LogUtil.log(TAG, "onAttach:" + context);

        super.onAttach(context);
    }

    @Override
    public void onAttachFragment(Fragment childFragment)
    {
        LogUtil.log(TAG, "onAttachFragment:" + childFragment);

        super.onAttachFragment(childFragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        LogUtil.log(TAG, "onCreate:" + savedInstanceState);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        LogUtil.log(TAG, "onActivityCreated:" + savedInstanceState);

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LogUtil.log(TAG, "onCreateView");

        return onCreateView(inflater, container);
    }

    public abstract View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        LogUtil.log(TAG, "onViewCreated");

        super.onViewCreated(view, savedInstanceState);

        mButterKnife = ButterKnife.bind(this, view);
    }

    @Override
    public void onStart()
    {
        LogUtil.log(TAG, "onStart");

        super.onStart();
    }

    @Override
    public void onResume()
    {
        LogUtil.log(TAG, "onResume");

        LogUtil.log(TAG, "---------------------------------->");

        super.onResume();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState)
    {
        LogUtil.log(TAG, "onViewStateRestored:" + savedInstanceState);

        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        LogUtil.log(TAG, "onSaveInstanceState:" + outState);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause()
    {
        LogUtil.log(TAG, "onPause");

        super.onPause();
    }

    @Override
    public void onStop()
    {
        LogUtil.log(TAG, "onStop");

        super.onStop();
    }

    @Override
    public void onDestroyView()
    {
        LogUtil.log(TAG, "onDestroyView");

        mButterKnife.unbind();

        super.onDestroyView();
    }

    @Override
    public void onDestroy()
    {
        LogUtil.log(TAG, "onDestroy");

        super.onDestroy();
    }

    @Override
    public void onDetach()
    {
        LogUtil.log(TAG, "onDetach");

        super.onDetach();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        LogUtil.logInfo(TAG, "onConfigurationChanged:" + newConfig);

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        LogUtil.log(TAG, "onHiddenChanged:" + hidden);

        super.onHiddenChanged(hidden);
    }
}
