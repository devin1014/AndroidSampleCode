package com.android.liuwei.myandroidcode.cookie;

import android.content.Context;
import android.text.TextUtils;

import com.android.liuwei.myandroidcode.OkHttpCookieStore;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * User: liuwei(wei.liu@neulion.com.com)
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

    private java.net.CookieManager mJavaNetCookieStore;

    private CookieSyncManagerCompat()
    {
        android.webkit.CookieManager.getInstance().setAcceptCookie(true);
    }

    public void setSyncCookieStore(Context context, OkHttpCookieStore cookieStore, java.net.CookieManager cookieHandler)
    {
        android.webkit.CookieSyncManager.createInstance(context);

        mOkHttpCookieStore = cookieStore;

        mJavaNetCookieStore = cookieHandler;
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
                String host = uri.getHost();

                List<Cookie> originalHttpCookies = Collections.unmodifiableList(mOkHttpCookieStore.getCookie(uri));

                //sync webview to OkHttp & java.net.URLConnection
                {
                    String webCookie = android.webkit.CookieManager.getInstance().getCookie(host);

                    if (!TextUtils.isEmpty(webCookie))
                    {
                        HttpUrl httpUrl = HttpUrl.get(uri);

                        String[] cookieArray = webCookie.split(";");

                        for (String c : cookieArray)
                        {
                            //to OkHttp
                            if (httpUrl != null)
                            {
                                mOkHttpCookieStore.addCookie(uri, Cookie.parse(httpUrl, c));
                            }
                            else
                            {
                                String[] pair = c.split("=");

                                String name = pair[0].trim();

                                String value = pair[1].trim();

                                mOkHttpCookieStore.addCookie(uri, new Cookie.Builder().domain(host).name(name).value(value).build());
                            }

                            //to URLConnection
                            //todo:sync
                        }
                    }
                }

                //sync okhttp to webview
                {
                    if (originalHttpCookies != null)
                    {
                        for (Cookie cookie : originalHttpCookies)
                        {
                            android.webkit.CookieManager.getInstance().setCookie(host, cookie.toString());
                        }

                        android.webkit.CookieSyncManager.getInstance().sync();
                    }
                }
            }
        }
    }
}
