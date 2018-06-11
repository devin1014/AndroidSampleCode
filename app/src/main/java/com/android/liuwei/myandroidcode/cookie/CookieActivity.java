package com.android.liuwei.myandroidcode.cookie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.liuwei.myandroidcode.BaseActivity;
import com.android.liuwei.myandroidcode.R;

import butterknife.BindView;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-05-04
 * Time: 15:54
 */
public class CookieActivity extends BaseActivity
{
    public static final String URL_VIP_SPORTS = "http://vip.sports.cctv.com/";
    public static final String URL_CHECK_SESSION="http://vip.sports.cctv.com/passport/checkSSO.do";
    public static final String URL_CHECK_DO="http://vip.sports.cctv.com/check.do";

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

        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class MyAdapter extends FragmentPagerAdapter
    {
        private final String[] TITLES = new String[]{"Cookie", "WebView", "Http"};

        MyAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            if (position == 0)
            {
                return new CookieFragment();
            }
            else if (position == 1)
            {
                return new WebViewFragment();
            }
            else
            {
                return new HttpFragment();
            }
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
}
