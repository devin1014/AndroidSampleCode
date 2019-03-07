package com.android.liuwei.myandroidcode.base;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * User: liuwei
 * Date: 2018-06-15
 * Time: 16:07
 */
public abstract class BaseLifeTrackFragment extends BaseFragment
{
    public BaseLifeTrackFragment()
    {
        LogUtil.log(this, "new " + getClass().getSimpleName() + "()");
    }

    @Override
    public void onAttach(Context context)
    {
        LogUtil.log(this, "onAttach:" + context);

        super.onAttach(context);
    }

    @Override
    public void onAttachFragment(Fragment childFragment)
    {
        LogUtil.log(this, "onAttachFragment:" + childFragment);

        super.onAttachFragment(childFragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        LogUtil.log(this, "onCreate:" + savedInstanceState);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        LogUtil.log(this, "onActivityCreated:" + savedInstanceState);

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LogUtil.log(this, "onCreateView");

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        LogUtil.log(this, "onViewCreated");

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart()
    {
        LogUtil.log(this, "onStart");

        super.onStart();
    }

    @Override
    public void onResume()
    {
        LogUtil.log(this, "onResume");

        LogUtil.log(this, "---------------------------------->");

        super.onResume();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState)
    {
        LogUtil.log(this, "onViewStateRestored:" + savedInstanceState);

        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        LogUtil.log(this, "onSaveInstanceState:" + outState);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause()
    {
        LogUtil.log(this, "onPause");

        super.onPause();
    }

    @Override
    public void onStop()
    {
        LogUtil.log(this, "onStop");

        super.onStop();
    }

    @Override
    public void onDestroyView()
    {
        LogUtil.log(this, "onDestroyView");

        super.onDestroyView();
    }

    @Override
    public void onDestroy()
    {
        LogUtil.log(this, "onDestroy");

        super.onDestroy();
    }

    @Override
    public void onDetach()
    {
        LogUtil.log(this, "onDetach");

        super.onDetach();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        LogUtil.info(this, "onConfigurationChanged:" + newConfig);

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        LogUtil.log(this, "onHiddenChanged:" + hidden);

        super.onHiddenChanged(hidden);
    }
}
