package com.android.liuwei.myandroidcode.feature.cookie;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * User: liuwei
 * Date: 2018-06-11
 * Time: 15:55
 */
public class OkHttpClientManager
{
    private static OkHttpClient sOkHttpClient;

    public static void init(Context context)
    {
        sOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .cookieJar(new PersistentCookieStore(context))
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    public static OkHttpClient getOkHttpClient()
    {
        return sOkHttpClient;
    }
}
