package com.android.liuwei.myandroidcode.cookie;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

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
    LogInUserNameThread(String url, HttpCallback callback)
    {
        super(url, callback);
    }

    @Override
    void request(String url)
    {
        if (url.contains("$"))
        {
            url = url.replace("${username}", "liuwei10074180@163.com");

            url = url.replace("${password}", "liuwei00");
        }

        Request request = new Request.Builder().url(url).build();

        Call call = OkHttpClientManager.getOkHttpClient().newCall(request);

        try
        {
            Response response = call.execute();

            String resultString = response.body().string();

            Logger.json(resultString);

            Gson gson = new GsonBuilder().create();

            LogInBean logInBean = gson.fromJson(resultString, LogInBean.class);

            if (logInBean != null && !TextUtils.isEmpty(logInBean.getTicket()))
            {
                notifyResult(resultString);

                HttpFragment.mTicket = logInBean.getTicket();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
