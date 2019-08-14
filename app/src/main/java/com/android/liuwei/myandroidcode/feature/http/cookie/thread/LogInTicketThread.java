package com.android.liuwei.myandroidcode.feature.http.cookie.thread;

import com.orhanobut.logger.Logger;

/**
 * User: liuwei
 * Date: 2018-06-12
 * Time: 15:35
 */
public class LogInTicketThread extends OkHttpThread
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

        connect(url);
    }
}
