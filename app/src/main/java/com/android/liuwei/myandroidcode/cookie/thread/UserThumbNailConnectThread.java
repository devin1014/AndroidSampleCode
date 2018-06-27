package com.android.liuwei.myandroidcode.cookie.thread;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-27
 * Time: 17:38
 */
public class UserThumbNailConnectThread extends URLConnectionThread
{
    public UserThumbNailConnectThread(String url, HttpCallback callback)
    {
        super(url, callback);
    }

    @Override
    public void request(String url)
    {
        connect(url);
    }
}
