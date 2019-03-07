package com.android.liuwei.myandroidcode.feature.cookie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;

/**
 * User: liuwei
 * Date: 2018-06-27
 * Time: 11:11
 */
public class CookieSyncManagerCompat
{
    private static class Holder
    {
        private static final CookieSyncManagerCompat INSTANCE = new CookieSyncManagerCompat();
    }

    public static CookieSyncManagerCompat getInstance()
    {
        return Holder.INSTANCE;
    }

    private Map<URI, List<String>> mSyncHostMap = new HashMap<>();

    private OkHttpCookieStore mOkHttpCookieStore;

    //private java.net.CookieManager mJavaNetCookieStore;

    private CookieSyncManagerCompat()
    {
        android.webkit.CookieManager.getInstance().setAcceptCookie(true);
    }

    public void setSyncCookieStore(Context context, OkHttpCookieStore cookieStore)
    {
        android.webkit.CookieSyncManager.createInstance(context);

        mOkHttpCookieStore = cookieStore;

        //mJavaNetCookieStore = cookieHandler;
    }

    public synchronized void addSyncHost(URI uri, String... cookieName)
    {
        if (!mSyncHostMap.containsKey(uri))
        {
            mSyncHostMap.put(uri, Arrays.asList(cookieName));
        }
    }

    public void sync()
    {
        if (mSyncHostMap.size() > 0)
        {
            for (URI uri : mSyncHostMap.keySet())
            {
                List<Cookie> originalHttpCookies = Collections.unmodifiableList(mOkHttpCookieStore.getCookie(uri));

                //sync webview to OkHttp & java.net.URLConnection
                {
                    String webkitCookie = android.webkit.CookieManager.getInstance().getCookie(uri.getHost());

                    if (!TextUtils.isEmpty(webkitCookie))
                    {
                        List<Cookie> cookies = parseWebkitCookie(uri, webkitCookie, mSyncHostMap.get(uri));

                        for (Cookie c : cookies)
                        {
                            mOkHttpCookieStore.addCookie(uri, c);

                            //todo:sync cookieHandler
                        }
                    }
                }

                //sync okhttp to webview
                if (originalHttpCookies != null && originalHttpCookies.size() > 0)
                {
                    List<String> cookies = parseOkHttpCookie(originalHttpCookies, mSyncHostMap.get(uri));

                    if (cookies.size() > 0)
                    {
                        for (String c : cookies)
                        {
                            android.webkit.CookieManager.getInstance().setCookie(uri.getHost(), c);
                        }

                        android.webkit.CookieSyncManager.getInstance().sync();
                    }
                }
            }
        }
    }

    private List<Cookie> parseWebkitCookie(@NonNull URI uri, String cookies, List<String> filter)
    {
        if (TextUtils.isEmpty(cookies))
        {
            return Collections.emptyList();
        }

        String[] arrays = cookies.split(";");

        List<Cookie> result = new ArrayList<>();

        for (String cookie : arrays)
        {
            boolean add = filter == null || filter.size() == 0;

            final String[] splits = cookie.trim().split("=");

            final String name = splits[0].trim();

            final String value = splits[1].trim();

            if (!add)
            {
                for (String f : filter)
                {
                    if (name.equalsIgnoreCase(f))
                    {
                        add = true;

                        break;
                    }
                }
            }

            if (add)
            {
                result.add(new Cookie.Builder().domain(uri.getHost()).name(name).value(value).build());
            }
        }

        return result;
    }

    private List<String> parseOkHttpCookie(List<Cookie> cookies, List<String> filter)
    {
        if (cookies == null || cookies.size() == 0)
        {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();

        for (Cookie cookie : cookies)
        {
            boolean add = filter == null || filter.size() == 0;

            if (!add)
            {
                for (String f : filter)
                {
                    if (cookie.name().equalsIgnoreCase(f))
                    {
                        add = true;

                        break;
                    }
                }
            }

            if (add)
            {
                result.add(cookie.toString());
            }
        }

        return result;
    }
}
