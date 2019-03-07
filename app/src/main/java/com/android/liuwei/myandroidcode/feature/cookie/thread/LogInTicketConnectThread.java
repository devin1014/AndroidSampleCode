package com.android.liuwei.myandroidcode.feature.cookie.thread;

import com.orhanobut.logger.Logger;

/**
 * User: liuwei
 * Date: 2018-06-27
 * Time: 17:29
 */
public class LogInTicketConnectThread extends URLConnectionThread
{
    private String mTicket;

    public LogInTicketConnectThread(String url, String ticket, HttpCallback callback)
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
