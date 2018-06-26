package com.android.liuwei.myandroidcode.cookie.thread;

import com.android.liuwei.myandroidcode.cookie.OkHttpClientManager;
import com.android.liuwei.myandroidcode.core.util.IOUtils;
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

    public LogInTicketThread(String url, String ticket, HttpCallback callback)
    {
        super(url, callback);

        mTicket = ticket;

        if (mTicket == null)
        {
            Logger.e("Ticket is NULL!");
        }
    }

    @Override
    public void request(String url)
    {
        url = url.replace("${ticket}", mTicket != null ? mTicket : "");

        Request request = new Request.Builder().url(url).build();

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
