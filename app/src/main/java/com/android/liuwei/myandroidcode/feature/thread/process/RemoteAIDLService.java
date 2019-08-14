package com.android.liuwei.myandroidcode.feature.thread.process;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.android.liuwei.myandroidcode.base.BaseService;

public class RemoteAIDLService extends BaseService
{
    private static final float OFFSET = 1.1F;

    private Binder mBinder = new CarManager.Stub()
    {
        @Override
        public float getPrice(Car car, float discount) throws RemoteException
        {
            return car.getPrice() * (discount * OFFSET);
        }
    };

    @Override
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }
}
