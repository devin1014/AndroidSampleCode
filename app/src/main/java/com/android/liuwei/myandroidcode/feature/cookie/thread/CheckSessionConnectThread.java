package com.android.liuwei.myandroidcode.feature.cookie.thread;

/**
 * User: liuwei
 * Date: 2018-06-27
 * Time: 17:33
 */
public class CheckSessionConnectThread extends URLConnectionThread
{
    public CheckSessionConnectThread(String url, HttpCallback callback)
    {
        super(url, callback);
    }

    @Override
    public void request(String url)
    {
        connect(url);
    }
}
