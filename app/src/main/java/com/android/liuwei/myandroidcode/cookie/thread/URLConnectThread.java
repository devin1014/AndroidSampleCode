package com.android.liuwei.myandroidcode.cookie.thread;

import com.android.liuwei.myandroidcode.core.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-05-04
 * Time: 16:31
 */
public class URLConnectThread extends BaseHttpThread
{
    URLConnectThread(String url, HttpCallback callback)
    {
        super(url, callback);
    }

    @Override
    public void request(String urlString)
    {
        InputStream inputStream = null;

        try
        {
            URL url = new URL(urlString);

            URLConnection urlConnection = url.openConnection();

            inputStream = urlConnection.getInputStream();

            final String result = IOUtils.parseInputStream(inputStream);

            notifyResult(result);
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
