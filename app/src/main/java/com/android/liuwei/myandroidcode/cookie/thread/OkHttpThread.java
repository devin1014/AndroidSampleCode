package com.android.liuwei.myandroidcode.cookie.thread;

import com.android.liuwei.myandroidcode.cookie.OkHttpClientManager;

import java.io.IOException;

import liuwei.android.core.util.IOUtils;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * User: liuwei
 * Date: 2018-06-27
 * Time: 17:40
 */
public abstract class OkHttpThread extends BaseHttpThread
{
    OkHttpThread(String url, HttpCallback callback)
    {
        super(url, callback);
    }

    protected String connect(String url)
    {
        notifyResult("正在加载。。。");

        String result = null;

        Request request = new Request.Builder().url(url).build();

        Call call = OkHttpClientManager.getOkHttpClient().newCall(request);

        try
        {
            Response response = call.execute();

            result = response.body().string();

            notifyResult(IOUtils.formatOkHttpResponse(response, result));
        }
        catch (IOException e)
        {
            e.printStackTrace();

            notifyResult(e.getMessage());
        }

        return result;
    }
}
