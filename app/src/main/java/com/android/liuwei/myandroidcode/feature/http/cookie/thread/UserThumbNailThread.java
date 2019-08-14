package com.android.liuwei.myandroidcode.feature.http.cookie.thread;

/**
 * User: liuwei
 * Date: 2018-06-12
 * Time: 15:42
 */
public class UserThumbNailThread extends OkHttpThread
{
    public UserThumbNailThread(String url, HttpCallback callback)
    {
        super(url, callback);
    }

    @Override
    public void request(String url)
    {
        connect(url);
    }
}
