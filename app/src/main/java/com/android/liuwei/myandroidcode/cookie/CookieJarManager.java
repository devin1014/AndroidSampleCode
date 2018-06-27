package com.android.liuwei.myandroidcode.cookie;

import android.content.Context;
import android.support.annotation.NonNull;

import java.net.CookieManager;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-11
 * Time: 14:58
 */
public class CookieJarManager extends CookieManager implements CookieJar
{
    public CookieJarManager(Context context)
    {
    }

    @Override
    public void saveFromResponse(@NonNull HttpUrl url, @NonNull List<Cookie> cookies)
    {
    }

    @Override
    public List<Cookie> loadForRequest(@NonNull HttpUrl url)
    {
        return null;
    }
}
