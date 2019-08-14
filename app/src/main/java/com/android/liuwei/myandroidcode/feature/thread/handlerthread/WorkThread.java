package com.android.liuwei.myandroidcode.feature.thread.handlerthread;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;

import com.android.liuwei.myandroidcode.base.LogUtil;

public class WorkThread extends Thread
{
    private Handler mHandler;
    private Looper mLooper;

    public WorkThread()
    {
        LogUtil.info(this, String.format(getClass().getSimpleName() + " Construct: [currentThread=%s]",
                Thread.currentThread().getName()));
    }

    @Override
    public void run()
    {
        LogUtil.info(this, "start");

        Looper.prepare();

        synchronized (this)
        {
            mLooper = Looper.myLooper();
            notifyAll();
        }

        onLooperPrepared(mLooper);

        Looper.loop();

        LogUtil.info(this, "end");
    }

    @SuppressWarnings({"WeakerAccess", "unused"})
    protected void onLooperPrepared(Looper looper)
    {
    }

    public boolean quit()
    {
        Looper looper = getLooper();
        if (looper != null)
        {
            looper.quit();
            return true;
        }
        return false;
    }

    public Handler getHandler()
    {
        if (mHandler == null)
        {
            mHandler = new Handler(getLooper(), new Callback()
            {
                @Override
                public boolean handleMessage(Message msg)
                {
                    LogUtil.info(this, String.format("handleMessage: %s", msg));

                    return true;
                }
            });
        }
        return mHandler;
    }

    public Looper getLooper()
    {
        if (!isAlive())
        {
            return null;
        }

        synchronized (this)
        {
            while (isAlive() && mLooper == null)
            {
                try
                {
                    wait();
                }
                catch (InterruptedException ignored)
                {
                }
            }
        }

        return mLooper;
    }
}
