package com.android.liuwei.myandroidcode.cookie.thread;

import com.android.liuwei.myandroidcode.cookie.CookieConstant;
import com.android.liuwei.myandroidcode.cookie.HttpRequestFragment;
import com.android.liuwei.myandroidcode.cookie.OkHttpClientManager;
import com.android.liuwei.myandroidcode.cookie.bean.LogInBean;
import com.android.liuwei.myandroidcode.core.util.IOUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-11
 * Time: 15:54
 */
public class LogInUserNameThread extends BaseHttpThread
{
    public LogInUserNameThread(String url, HttpCallback callback)
    {
        super(url, callback);
    }

    @Override
    public void request(String url)
    {
        if (url.contains("$"))
        {
            url = url.replace("${username}", CookieConstant.SPORTS_VIP_USERNAME);

            url = url.replace("${password}", CookieConstant.SPORTS_VIP_PASSWORD);
        }

        Request request = new Request.Builder().url(url).build();

        Call call = OkHttpClientManager.getOkHttpClient().newCall(request);

        try
        {
            Response response = call.execute();

            String result = response.body().string();

            String resultString = IOUtils.formatOkHttpResponse(response, result);

            notifyResult(resultString);

            Gson gson = new GsonBuilder().create();

            LogInBean logInBean = gson.fromJson(result, LogInBean.class);

            HttpRequestFragment.mTicket = logInBean.getTicket();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
