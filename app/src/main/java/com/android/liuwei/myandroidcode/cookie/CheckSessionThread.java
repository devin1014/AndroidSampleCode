package com.android.liuwei.myandroidcode.cookie;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-11
 * Time: 14:19
 */
public class CheckSessionThread extends BaseHttpThread
{
    CheckSessionThread(String url, HttpCallback callback)
    {
        super(url, callback);
    }

    @Override
    public void request(String url)
    {
        Request.Builder builder = new Request.Builder();

        Request request = builder.url(url).build();

        try
        {
            Call call = OkHttpClientManager.getOkHttpClient().newCall(request);

            Response response = call.execute();

            if (response != null && response.body() != null)
            {
                notifyResult(response.body().string());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
