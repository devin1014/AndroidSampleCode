package com.android.liuwei.myandroidcode.cookie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.SparseArray;

import com.android.liuwei.myandroidcode.OkHttpCookieStore;
import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.core.base.BaseActivity;
import com.android.liuwei.myandroidcode.core.base.BasePageFragment;

import java.net.URI;

import butterknife.BindView;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-05-04
 * Time: 15:54
 */
public class CookieActivity extends BaseActivity
{
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_cookie;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);

        CookieSyncManagerCompat.getInstance().addSyncHost(URI.create(CookieConstant.URL_VIP_SPORTS));
        CookieSyncManagerCompat.getInstance().setSyncCookieStore(this, (OkHttpCookieStore) OkHttpClientManager.getOkHttpClient().cookieJar());
    }

    private class MyAdapter extends FragmentStatePagerAdapter
    {
        private final String[] TITLES = new String[]{"Cookie", "WebView", "Http"};

        private SparseArray<BasePageFragment> mSparseArray = new SparseArray<>(3);

        MyAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            BasePageFragment fragment = mSparseArray.get(position);

            if (fragment == null)
            {
                if (position == 0)
                {
                    fragment = new CookieListFragment();
                }
                else if (position == 1)
                {
                    fragment = new WebViewFragment();
                }
                else
                {
                    fragment = new HttpRequestFragment();
                }

                mSparseArray.put(position, fragment);
            }

            return fragment;
        }

        @Override
        public int getCount()
        {
            return TITLES.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position)
        {
            return TITLES[position];
        }
    }

    private OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener()
    {
        private int mCurrentPosition = -1;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {
        }

        @Override
        public void onPageSelected(int position)
        {
            FragmentStatePagerAdapter adapter = ((FragmentStatePagerAdapter) mViewPager.getAdapter());

            if (mCurrentPosition != -1)
            {
                BasePageFragment fragment = (BasePageFragment) adapter.getItem(mCurrentPosition);

                fragment.onUnselected();
            }

            BasePageFragment fragment = (BasePageFragment) adapter.getItem(position);

            fragment.onSelected();

            mCurrentPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state)
        {
        }
    };
}
