/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/liuwei/Code/android/myandroidcode/app/src/main/aidl/com/android/liuwei/myandroidcode/feature/process/car/CarManager.aidl
 */
package com.android.liuwei.myandroidcode.feature.process.car;

import android.support.annotation.NonNull;

public interface _MyCarManager extends android.os.IInterface
{
    float getPrice(Car car, float discount) throws android.os.RemoteException;

    String getName() throws android.os.RemoteException;

    /**
     * Local-side IPC implementation stub class.
     */
    abstract class Stub extends android.os.Binder implements _MyCarManager
    {
        private static final String DESCRIPTOR = "com.android.liuwei.myandroidcode.feature.process.car._MyCarManager";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub()
        {
            this.attachInterface(this, DESCRIPTOR);
        }

        @Override
        public android.os.IBinder asBinder()
        {
            return this;
        }

        @Override
        public boolean onTransact(int code,
                                  @NonNull android.os.Parcel data,
                                  android.os.Parcel reply,
                                  int flags) throws android.os.RemoteException
        {
            switch (code)
            {
                case INTERFACE_TRANSACTION:
                {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_getPrice:
                {
                    data.enforceInterface(DESCRIPTOR);
                    Car _arg0;
                    if ((0 != data.readInt()))
                    {
                        _arg0 = Car.CREATOR.createFromParcel(data);
                    }
                    else
                    {
                        _arg0 = null;
                    }
                    float _arg1;
                    _arg1 = data.readFloat();
                    float _result = this.getPrice(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeFloat(_result);
                    return true;
                }
                case TRANSACTION_getName:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _result = this.getName();
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        /**
         * Cast an IBinder object into an com.android.liuwei.myandroidcode.feature.process.car.CarManager interface,
         * generating a proxy if needed.
         */
        public static _MyCarManager asInterface(android.os.IBinder obj)
        {
            if ((obj == null))
            {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin instanceof _MyCarManager)))
            {
                return ((_MyCarManager) iin);
            }
            return new Stub.Proxy(obj);
        }

        private static class Proxy implements _MyCarManager
        {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote)
            {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder()
            {
                return mRemote;
            }

            public String getInterfaceDescriptor()
            {
                return DESCRIPTOR;
            }

            @Override
            public float getPrice(Car car, float discount) throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                float _result;
                try
                {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((car != null))
                    {
                        _data.writeInt(1);
                        car.writeToParcel(_data, 0);
                    }
                    else
                    {
                        _data.writeInt(0);
                    }
                    _data.writeFloat(discount);
                    mRemote.transact(Stub.TRANSACTION_getPrice, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readFloat();
                }
                finally
                {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public String getName() throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                String _result;
                try
                {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getName, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readString();
                }
                finally
                {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }

        static final int TRANSACTION_getPrice = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_getName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    }
}
