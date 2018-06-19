package com.android.liuwei.myandroidcode.cookie;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-12
 * Time: 15:42
 */
public class AccountThumbnail extends BaseHttpThread
{
    AccountThumbnail(String url, HttpCallback callback)
    {
        super(url, callback);
    }

    @Override
    void request(String url)
    {
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
            //            LogInBean logInBean = gson.fromJson(resultString, LogInBean.class);
            //
            //            if (logInBean != null && !TextUtils.isEmpty(logInBean.getTicket()))
            //            {
            //            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
