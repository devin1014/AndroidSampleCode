package com.android.liuwei.myandroidcode.feature.widget.viewpager;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("CommitTransaction")
public abstract class FragmentPersistPageAdapter extends PagerAdapter
{
    private static final String TAG = "FragmentPersistPageAdapter";
    private static final boolean DEBUG = true;
    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;

    public FragmentPersistPageAdapter(FragmentManager fm)
    {
        mFragmentManager = fm;
    }

    public abstract Fragment getItem(int var1);

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        if (mCurTransaction == null)
        {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        long itemId = getItemId(position);
        String name = makeFragmentName(container.getId(), itemId);
        Fragment fragment = mFragmentManager.findFragmentByTag(name);
        if (fragment != null)
        {
            mCurTransaction.show(fragment);
        }
        else
        {
            fragment = getItem(position);
            mCurTransaction.add(container.getId(), fragment, makeFragmentName(container.getId(), itemId));
        }

        if (fragment != mCurrentPrimaryItem)
        {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }

        return fragment;
    }

    public long getItemId(int position)
    {
        return position;
    }

    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        if (mCurTransaction == null)
        {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        mCurTransaction.hide((Fragment) object);
    }

    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentPrimaryItem)
        {
            if (mCurrentPrimaryItem != null)
            {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }

            fragment.setMenuVisibility(true);
            fragment.setUserVisibleHint(true);
            mCurrentPrimaryItem = fragment;
        }
    }

    public void startUpdate(@NonNull ViewGroup container)
    {
        if (container.getId() == -1)
        {
            throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
        }
    }

    public void finishUpdate(@NonNull ViewGroup container)
    {
        if (mCurTransaction != null)
        {
            mCurTransaction.commitNowAllowingStateLoss();
            mCurTransaction = null;
        }
    }

    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return ((Fragment) object).getView() == view;
    }

    private static String makeFragmentName(int viewId, long id)
    {
        return "android:switcher:" + viewId + ":" + id;
    }
}
