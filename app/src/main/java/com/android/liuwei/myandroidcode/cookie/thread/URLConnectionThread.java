package com.android.liuwei.myandroidcode.cookie.thread;

import com.android.liuwei.myandroidcode.core.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-27
 * Time: 17:31
 */
public abstract class URLConnectionThread extends BaseHttpThread
{
    URLConnectionThread(String url, HttpCallback callback)
    {
        super(url, callback);
    }

    protected String connect(String url)
    {
        notifyResult("正在加载");

        InputStream inputStream = null;

        try
        {
            URLConnection connection = new URL(url).openConnection();

            inputStream = connection.getInputStream();

            String header = IOUtils.parseURLConnectionResponseHeader(connection);

            String result = IOUtils.parseURLConnectionResponse(inputStream);

            notifyResult(header + "\n\n" + result);

            return result;
        }
        catch (IOException e)
        {
            e.printStackTrace();

            notifyResult(e.getMessage());
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

        return null;
    }
}
