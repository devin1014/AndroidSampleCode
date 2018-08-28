package liuwei.android.core.base;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import liuwei.android.core.util.LogUtil;


/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-15
 * Time: 16:07
 */
public abstract class BaseLifeTrackFragment extends BaseFragment
{
    private final String TAG = getClass().getSimpleName();

    public BaseLifeTrackFragment()
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

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        LogUtil.log(TAG, "onViewCreated");

        super.onViewCreated(view, savedInstanceState);
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
        LogUtil.info(TAG, "onConfigurationChanged:" + newConfig);

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        LogUtil.log(TAG, "onHiddenChanged:" + hidden);

        super.onHiddenChanged(hidden);
    }
}
