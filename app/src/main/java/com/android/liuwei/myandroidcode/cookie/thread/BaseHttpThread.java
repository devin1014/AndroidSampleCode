package com.android.liuwei.myandroidcode.cookie.thread;

import android.os.Handler;
import android.os.Looper;

/**
 * User: liuwei
 * Date: 2018-06-11
 * Time: 14:20
 */
public abstract class BaseHttpThread extends Thread
{
    private String mUrl;

    private HttpCallback mCallback;

    public interface HttpCallback
    {
        void onResult(String result);
    }

    private Handler mHandler;

    BaseHttpThread(String url, HttpCallback callback)
    {
        mUrl = url;

        mCallback = callback;

        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public final void run()
    {
        request(mUrl);
    }

    public abstract void request(String url);

    final void notifyResult(final String response)
    {
        if (mCallback != null)
        {
            mHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    if (mCallback != null)
                    {
                        mCallback.onResult(response);
                    }
                }
            });
        }
    }
}
