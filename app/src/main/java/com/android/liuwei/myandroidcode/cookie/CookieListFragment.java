package com.android.liuwei.myandroidcode.cookie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.OkHttpCookieStore;
import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.core.base.BasePageFragment;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Cookie;
import okhttp3.CookieJar;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-04
 * Time: 14:57
 */
public class CookieListFragment extends BasePageFragment
{
    @BindView(R.id.cookie_wb_value)
    TextView mWebViewCookie;
    @BindView(R.id.cookie_http_value)
    TextView mHttpCookie;
    @BindView(R.id.cookie_java_value)
    TextView mJavaCookie;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saveInstance)
    {
        return inflater.inflate(R.layout.fragment_cookie_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        if (CookieHandler.getDefault() == null)
        {
            CookieHandler.setDefault(new CookieManager());
        }

        resetCookies();
    }

    private void resetCookies()
    {
        //web view
        {
            URI uri = URI.create(CookieConstant.URL_VIP_SPORTS);

            String cookie = android.webkit.CookieManager.getInstance().getCookie(uri.getHost());

            StringBuilder builder = new StringBuilder();

            if (!TextUtils.isEmpty(cookie))
            {
                String[] arrays = cookie.split(";");

                for (String s : arrays)
                {
                    builder.append(URLDecoder.decode(s)).append("\n");
                }
            }

            mWebViewCookie.setText(builder.toString());
        }

        //java.net http
        {
            CookieManager cookieManager = (CookieManager) CookieHandler.getDefault();

            CookieStore cookieStore = cookieManager.getCookieStore();

            List<HttpCookie> cookieList = cookieStore.getCookies();

            if (cookieList != null)
            {
                StringBuilder builder = new StringBuilder();

                for (HttpCookie cookie : cookieList)
                {
                    builder.append(cookie.toString()).append("\n");
                }

                mJavaCookie.setText(builder.toString());
            }
        }

        //okhttp
        {
            CookieJar cookieJar = OkHttpClientManager.getOkHttpClient().cookieJar();

            if (cookieJar instanceof OkHttpCookieStore)
            {
                StringBuilder builder = new StringBuilder();

                for (String url : ((OkHttpCookieStore) cookieJar).getCookieDomainUrl())
                {
                    if (builder.length() > 0)
                    {
                        builder.append("\n");
                    }

                    builder.append(url).append("\n");

                    List<Cookie> cookies = ((OkHttpCookieStore) cookieJar).getCookie(url);

                    for (Cookie c : cookies)
                    {
                        builder.append(c).append("\n");
                    }
                }

                mHttpCookie.setText(builder.toString());
            }
        }
    }

    @OnClick(R.id.cookie_clear)
    public void clearCookies()
    {
        CookieJar cookieJar = OkHttpClientManager.getOkHttpClient().cookieJar();

        if (cookieJar instanceof OkHttpCookieStore)
        {
            ((OkHttpCookieStore) cookieJar).removeAllCookie();
        }

        android.webkit.CookieManager.getInstance().removeAllCookie();

        resetCookies();
    }

    @Override
    public void onSelected()
    {
        resetCookies();
    }
}
