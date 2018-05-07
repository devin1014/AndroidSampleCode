package com.android.liuwei.myandroidcode.cookie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.BaseActivity;
import com.android.liuwei.myandroidcode.R;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.List;

import butterknife.BindView;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-05-04
 * Time: 15:54
 */
public class CookieActivity extends BaseActivity implements ConnectCallback
{
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.cookie_value)
    TextView mCookieTextView;
    @BindView(R.id.webview_cookie_value)
    TextView mWebViewCookieTextView;

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_cookie;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        //CookieSyncManager.createInstance(this);
        //CookieSyncManager.getInstance().sync();
        android.webkit.CookieManager.getInstance().setAcceptCookie(true);

        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onConnectComplete(String url)
    {
        //url connection
        mCookieTextView.setText("");

        CookieManager cookieManager = (CookieManager) CookieHandler.getDefault();

        CookieStore cookieStore = cookieManager.getCookieStore();

        List<HttpCookie> cookieList = cookieStore.getCookies();

        if (cookieList != null)
        {
            for (HttpCookie cookie : cookieList)
            {
                mCookieTextView.append(cookie.toString() + "\n");
            }
        }

        //web view
        mWebViewCookieTextView.setText("");

        android.webkit.CookieManager.getInstance().getCookie("");
    }

    private class MyAdapter extends FragmentPagerAdapter
    {
        MyAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            if (position == 0)
            {
                return new URLConnectionFragment();
            }
            else
            {
                return new WebViewFragment();
            }
        }

        @Override
        public int getCount()
        {
            return 2;
        }
    }
}
