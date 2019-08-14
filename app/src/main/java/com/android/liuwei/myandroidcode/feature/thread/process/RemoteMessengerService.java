package com.android.liuwei.myandroidcode.feature.thread.process;

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

public class RemoteMessengerService extends BaseService
{
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        super.onBind(intent);

        return mMessenger.getBinder();
    }

    private Messenger mMessenger = new Messenger(new MessagerHandler());

    private static class MessagerHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            Bundle data = msg.getData();

            data.setClassLoader(Car.class.getClassLoader());

            float discount = data.getFloat("msg") / 10f * 1.1f;

            Car car = data.getParcelable("car");

            LogUtil.info(this, String.format("msg=%s", discount));

            LogUtil.info(this, String.format("car=%s", car));

            Messenger replyMessenger = msg.replyTo;

            Message replyMsg = Message.obtain();

            replyMsg.what = msg.what;

            Bundle replyBundle = new Bundle();

            replyBundle.putFloat("msg", discount);

            assert car != null;
            replyBundle.putParcelable("car", car);

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
