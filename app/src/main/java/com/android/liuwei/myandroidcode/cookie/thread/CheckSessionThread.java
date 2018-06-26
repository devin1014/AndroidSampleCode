package com.android.liuwei.myandroidcode.cookie.thread;

import com.android.liuwei.myandroidcode.cookie.OkHttpClientManager;
import com.android.liuwei.myandroidcode.core.util.IOUtils;

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
    public CheckSessionThread(String url, HttpCallback callback)
    {
        super(url, callback);
    }

    @Override
    public void request(String url)
    {
        Request.Builder builder = new Request.Builder();

        Request request = builder.url(url).build();

        Call call = OkHttpClientManager.getOkHttpClient().newCall(request);

        try
        {
            Response response = call.execute();

            String resultString = IOUtils.formatOkHttpResponse(response, response.body().string());

            notifyResult(resultString);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
