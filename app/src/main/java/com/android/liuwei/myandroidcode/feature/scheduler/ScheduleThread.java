package com.android.liuwei.myandroidcode.feature.scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
class ScheduleThread extends Thread
{
    private final BlockingQueue<ScheduleTask> mQueue;
    private final Schedulers mSchedulers;

    ScheduleThread(BlockingQueue<ScheduleTask> queue, Schedulers schedulers)
    {
        mQueue = queue;
        mSchedulers = schedulers;
    }

    @Override
    public void run()
    {
        Logger.getLogger(getClass().getSimpleName()).log(Level.INFO, "start!");

        while (mSchedulers != null && mSchedulers.isActive())
        {
            ScheduleTask task = mQueue.peek();

            if (task != null)
            {
                if (task.shouldTrigger())
                {
                    mQueue.remove(task);

                    task.resetTriggerTime();

                    mQueue.add(task);

                    task.run();
                }
            }

            sleepShortTime();
        }

        Logger.getLogger(getClass().getSimpleName()).log(Level.INFO, "end!");
    }

    private void sleepShortTime()
    {
        try
        {
            sleep(100L);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
