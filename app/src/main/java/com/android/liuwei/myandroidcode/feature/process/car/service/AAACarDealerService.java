package com.android.liuwei.myandroidcode.feature.process.car.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.android.liuwei.myandroidcode.base.BaseService;
import com.android.liuwei.myandroidcode.feature.process.car.Car;
import com.android.liuwei.myandroidcode.feature.process.car.CarManager.Stub;

public class AAACarDealerService extends BaseService
{
    private static final float OFFSET = 1F;

    @SuppressWarnings("RedundantThrows")
    private Binder mBinder = new Stub()
    {
        @Override
        public float getPrice(Car car, float discount) throws RemoteException
        {
            return car.getPrice() * (discount * OFFSET);
        }

        @Override
        public String getName() throws RemoteException
        {
            return "AAA";
        }
    };

    @Override
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }
}
