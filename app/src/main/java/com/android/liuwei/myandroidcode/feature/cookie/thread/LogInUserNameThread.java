package com.android.liuwei.myandroidcode.feature.cookie.thread;

import android.text.TextUtils;

import com.android.liuwei.myandroidcode.feature.cookie.CookieConstant;
import com.android.liuwei.myandroidcode.feature.cookie.HttpRequestFragment;
import com.android.liuwei.myandroidcode.feature.cookie.bean.LogInBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * User: liuwei
 * Date: 2018-06-11
 * Time: 15:54
 */
public class LogInUserNameThread extends OkHttpThread
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

        String result = connect(url);

        if (!TextUtils.isEmpty(result))
        {
            Gson gson = new GsonBuilder().create();

            LogInBean logInBean = gson.fromJson(result, LogInBean.class);

            HttpRequestFragment.mTicket = logInBean.getTicket();
        }
    }
}
