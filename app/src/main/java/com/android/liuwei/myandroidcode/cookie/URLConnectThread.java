package com.android.liuwei.myandroidcode.cookie;

import android.os.Handler;
import android.os.Looper;

import com.android.liuwei.myandroidcode.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-05-04
 * Time: 16:31
 */
public class URLConnectThread extends Thread
{
    private String mConnectionUrl;

    private URLConnectionCallback mCallback;

    public interface URLConnectionCallback
    {
        void onResult(String result);
    }

    private Handler mHandler;

    URLConnectThread(String url, URLConnectionCallback callback)
    {
        mConnectionUrl = url;

        mCallback = callback;

        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run()
    {
        InputStream inputStream = null;

        try
        {
            URL url = new URL(mConnectionUrl);

            URLConnection urlConnection = url.openConnection();

            inputStream = urlConnection.getInputStream();

            final String result = IOUtils.parseInputStream(inputStream);

            mHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    if (mCallback != null)
                    {
                        mCallback.onResult(result);
                    }

                    mCallback = null;
                }
            });
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (inputStream != null)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
