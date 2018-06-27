package com.android.liuwei.myandroidcode.cookie.thread;

/**
 * User: liuwei(wei.liu@neulion.com.com)
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
