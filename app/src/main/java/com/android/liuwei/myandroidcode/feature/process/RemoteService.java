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

            data.setClassLoader(CarEntity.class.getClassLoader());

            String text = data.getString("msg");

            CarEntity carEntity = data.getParcelable("car");

            LogUtil.info(this, String.format("msg=%s", text));

            LogUtil.info(this, String.format("car=%s", carEntity));

            Messenger replyMessenger = msg.replyTo;

            Message replyMsg = Message.obtain();

            replyMsg.what = msg.what;

            Bundle replyBundle = new Bundle();

            replyBundle.putString("msg", String.valueOf(System.currentTimeMillis()));

            assert carEntity != null;
            replyBundle.putParcelable("car", resetCar(carEntity));

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

        private CarEntity resetCar(CarEntity carEntity)
        {
            if (carEntity.getName().equalsIgnoreCase("BMW"))
            {
                carEntity.setPrice(29.9f);
            }
            else if (carEntity.getName().equalsIgnoreCase("BENZ"))
            {
                carEntity.setPrice(33.4f);
            }
            else if (carEntity.getName().equalsIgnoreCase("AUDI"))
            {
                carEntity.setPrice(28.1f);
            }
            else
            {
                carEntity.setPrice(-1f);
            }

            return carEntity;
        }
    }
}
