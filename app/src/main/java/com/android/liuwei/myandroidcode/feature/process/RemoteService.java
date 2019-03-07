package com.android.liuwei.myandroidcode.feature.process;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.android.liuwei.myandroidcode.base.BaseService;
import com.android.liuwei.myandroidcode.base.LogUtil;

public class RemoteService extends BaseService
{
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        LogUtil.info(this, "onBind");

        return mMessenger.getBinder();
    }

    private Messenger mMessenger = new Messenger(new MessagerHandler());

    private static class MessagerHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);

            LogUtil.info(this, String.format("handleMessage[%s]", msg.toString()));

            String text = msg.getData().getString("msg");

            LogUtil.info(this, text);

            Messenger replyMessenger = msg.replyTo;

            Message replyMsg = Message.obtain();

            replyMsg.what = msg.what;

            Bundle replyBundle = new Bundle();

            replyBundle.putString("msg", String.valueOf(System.currentTimeMillis()));

            replyMsg.setData(replyBundle);

            try
            {
                replyMessenger.send(replyMsg);
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }
}
