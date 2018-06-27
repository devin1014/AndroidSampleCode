package com.android.liuwei.myandroidcode.cookie;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

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

    private OkHttpCookieStore mHttpCookieStore;

    private CookieSyncManagerCompat()
    {
        CookieManager.getInstance().setAcceptCookie(true);
    }

    public void setSyncCookieStore(Context context, OkHttpCookieStore cookieStore)
    {
        CookieSyncManager.createInstance(context);

        mHttpCookieStore = cookieStore;
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

                List<Cookie> originalHttpCookies = Collections.unmodifiableList(mHttpCookieStore.getCookie(uri));

                //sync webview to okhttp
                {
                    String webCookie = CookieManager.getInstance().getCookie(host);

                    if (!TextUtils.isEmpty(webCookie))
                    {
                        HttpUrl httpUrl = HttpUrl.get(uri);

                        String[] cookieArray = webCookie.split(";");

                        for (String c : cookieArray)
                        {
                            if (httpUrl != null)
                            {
                                mHttpCookieStore.addCookie(uri, Cookie.parse(httpUrl, c));
                            }
                            else
                            {
                                String[] pair = c.split("=");

                                String name = pair[0].trim();

                                String value = pair[1].trim();

                                mHttpCookieStore.addCookie(uri, new Cookie.Builder().domain(host).name(name).value(value).build());
                            }
                        }
                    }
                }

                //sync okhttp to webview
                {
                    if (originalHttpCookies != null)
                    {
                        for (Cookie cookie : originalHttpCookies)
                        {
                            CookieManager.getInstance().setCookie(host, cookie.toString());
                        }

                        CookieSyncManager.getInstance().sync();
                    }
                }
            }
        }
    }
}
