package com.android.liuwei.myandroidcode.feature.cookie.thread;

/**
 * User: liuwei
 * Date: 2018-06-11
 * Time: 14:19
 */
public class CheckSessionThread extends OkHttpThread
{
    public CheckSessionThread(String url, HttpCallback callback)
    {
        super(url, callback);
    }

    @Override
    public void request(String url)
    {
        connect(url);
    }

}
