package com.android.liuwei.myandroidcode.cookie;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-12
 * Time: 15:35
 */
public class LogInTicketThread extends BaseHttpThread
{
    private String mTicket;

    LogInTicketThread(String url, String ticket, HttpCallback callback)
    {
        super(url, callback);

        mTicket = ticket;
    }

    @Override
    void request(String url)
    {
        url = url.replace("${ticket}", mTicket);

        Request request = new Request.Builder().url(url).build();

        Call call = OkHttpClientManager.getOkHttpClient().newCall(request);

        try
        {
            Response response = call.execute();

            String resultString = response.body().string();

            Logger.json(resultString);

            notifyResult(resultString);

            //            Gson gson = new GsonBuilder().create();
            //
            //            LogInTicketBean logInBean = gson.fromJson(resultString, LogInTicketBean.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
