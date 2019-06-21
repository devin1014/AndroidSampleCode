package com.android.liuwei.myandroidcode.feature.scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
class Schedulers
{
    private static class Holder
    {
        private static final Schedulers INSTANCE = new Schedulers();
    }

    public static Schedulers getInstance()
    {
        return Holder.INSTANCE;
    }

    private BlockingQueue<ScheduleTask> mBlockingQueue;

    private Thread mScheduleThread;

    public void addToQueue(ScheduleTask task)
    {
        if (mBlockingQueue == null)
        {
            mBlockingQueue = new PriorityBlockingQueue<>();

            mScheduleThread = new ScheduleThread(mBlockingQueue, this);

            mScheduleThread.start();
        }

        mBlockingQueue.add(task);

        Logger.getLogger(getClass().getName()).log(Level.INFO, String.format("addToQueue , %s", mBlockingQueue.toString()));
    }

    public void removeFromQueue(ScheduleTask task)
    {
        if (mBlockingQueue != null)
        {
            mBlockingQueue.remove(task);

            Logger.getLogger(getClass().getName()).log(Level.INFO, String.format("removeFromQueue , %s", mBlockingQueue.toString()));
        }
    }

    private boolean mActive = true;


    public boolean isActive()
    {
        return mActive;
    }

    public void shutdown()
    {
        mActive = false;

        if (mScheduleThread != null)
        {
            mScheduleThread.interrupt();
        }
    }
}
