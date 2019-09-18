package com.android.liuwei.myandroidcode.feature.process.car.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;

import com.android.liuwei.myandroidcode.base.LogUtil;
import com.android.liuwei.myandroidcode.feature.process.car.Car;
import com.android.liuwei.myandroidcode.feature.process.car.CarManager;
import com.android.liuwei.myandroidcode.feature.process.car._MyCarManager;

public abstract class MyServiceConnection implements ServiceConnection
{
    @Override
    public void onServiceConnected(ComponentName name, IBinder service)
    {
        LogUtil.info(this, String.format("onServiceConnected(%s)", name.getShortClassName()));
    }

    @Override
    public void onServiceDisconnected(ComponentName name)
    {
        LogUtil.warn(this, String.format("onServiceDisconnected(%s)", name.getShortClassName()));
    }


    public static class AAACarDealerServiceConnection extends MyServiceConnection implements CarManager
    {
        CarManager mAAACarManager;

        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            super.onServiceConnected(name, service);
            mAAACarManager = CarManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            super.onServiceDisconnected(name);
            mAAACarManager = null;
        }

        @Override
        public float getPrice(Car car, float discount) throws RemoteException
        {
            return mAAACarManager.getPrice(car, discount);
        }

        @Override
        public String getName() throws RemoteException
        {
            return mAAACarManager.getName();
        }

        @Override
        public IBinder asBinder()
        {
            return mAAACarManager.asBinder();
        }
    }

    public static class BBBCarDealerServiceConnection extends MyServiceConnection implements _MyCarManager
    {
        _MyCarManager mBBBCarManager;

        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            super.onServiceConnected(name, service);
            mBBBCarManager = _MyCarManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            super.onServiceDisconnected(name);
            mBBBCarManager = null;
        }

        @Override
        public float getPrice(Car car, float discount) throws RemoteException
        {
            return mBBBCarManager.getPrice(car, discount);
        }

        @Override
        public String getName() throws RemoteException
        {
            return mBBBCarManager.getName();
        }

        @Override
        public IBinder asBinder()
        {
            return mBBBCarManager.asBinder();
        }
    }

    public static class CCCCarDealerServiceConnection extends MyServiceConnection
    {
        public Messenger mMessenger;

        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            super.onServiceConnected(name, service);
            mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            super.onServiceDisconnected(name);
            mMessenger = null;
        }
    }
}
