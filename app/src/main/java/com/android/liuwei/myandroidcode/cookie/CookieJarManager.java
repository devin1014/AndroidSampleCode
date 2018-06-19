package com.android.liuwei.myandroidcode.cookie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.orhanobut.logger.Logger;

import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-11
 * Time: 14:58
 */
public class CookieJarManager implements CookieJar
{
    private final PersistentCookieStore mCookieStore;

    private final CookieManager mCookieManager;

    public CookieJarManager(Context context)
    {
        mCookieStore = new PersistentCookieStore(context);

        //TODO,android api
        CookieSyncManager.createInstance(context);

        mCookieManager = CookieManager.getInstance();
        mCookieManager.setAcceptCookie(true);
    }

    @Override
    public void saveFromResponse(@NonNull HttpUrl url, @NonNull List<Cookie> cookies)
    {
        Logger.d("CookieJar-> saveFromResponse():" + url.uri().getHost() + "," + cookies);

        if (cookies.size() > 0)
        {
            for (Cookie item : cookies)
            {
                mCookieStore.add(url, item);

                //set cookie to webview store
                mCookieManager.setCookie(url.host(), item.name() + "=" + item.value());
            }

            CookieSyncManager.getInstance().sync();
        }
    }

    @Override
    public List<Cookie> loadForRequest(@NonNull HttpUrl url)
    {
        List<Cookie> cookies = mCookieStore.get(url);

        if (cookies == null)
        {
            cookies = Collections.emptyList();
        }

        Logger.d("CookieJar-> loadForRequest():" + url.host() + "," + cookies);

        String webCookie = mCookieManager.getCookie(url.host());

        if (!TextUtils.isEmpty(webCookie))
        {
            String[] cookieArray = webCookie.split(";");

            for (String c : cookieArray)
            {
                String[] pair = c.split("=");

                String domain = url.host().trim();

                String name = pair[0].trim();

                String value = pair[1].trim();

                cookies.add(new Cookie.Builder().domain(domain).name(name).value(value).build());
            }
        }

        return cookies;
    }

    public PersistentCookieStore getCookieStore()
    {
        return mCookieStore;
    }
}
