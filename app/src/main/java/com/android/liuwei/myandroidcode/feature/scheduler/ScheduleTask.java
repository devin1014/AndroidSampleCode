package com.android.liuwei.myandroidcode.feature.scheduler;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
class ScheduleTask implements Runnable, Comparable<ScheduleTask>
{
    private final String TAG = getClass().getSimpleName();
    private static final int NONE = 0;
    private static final int ACTIVITY_PAUSE = 1;
    private static final int ACTIVITY_STOP = 2;

    private final String tag;
    private final long scheduleIntervalMillSec;
    private final int pauseCondition;
    private final long taskScheduleTime;
    private final boolean notifyInUIThread;
    private final boolean debugLog;
    private long nextTriggerTime;
    private long freezeDurationMillSec = 0L;
    private Runnable runnable;
    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    private ScheduleTask(Builder builder)
    {
        this.tag = builder.tag;
        this.scheduleIntervalMillSec = builder.scheduleIntervalMillSec;
        this.pauseCondition = builder.pauseCondition;
        this.taskScheduleTime = currentTimeMillis();
        this.notifyInUIThread = builder.notifyInUIThread;
        this.debugLog = builder.debugLog;
        final long delayMillSec = builder.delayMillSec;
        this.nextTriggerTime = delayMillSec > 0 ? currentTimeMillis() + delayMillSec
                : currentTimeMillis() + scheduleIntervalMillSec;
        this.runnable = builder.runnable;
        log(String.format("create , interval= %s ms", scheduleIntervalMillSec));
    }

    // ----------------------------------------------------------------
    // - Task
    // ----------------------------------------------------------------
    public void start()
    {
        log("start");
        Schedulers.getInstance().addToQueue(this);
    }

    public void stop()
    {
        log("stop");
        Schedulers.getInstance().removeFromQueue(this);
    }

    private long mPauseTimeStamp = 0L;

    public void pause()
    {
        log(String.format("pause , remain= %s ms", (nextTriggerTime - currentTimeMillis())));
        mPauseTimeStamp = currentTimeMillis();
    }

    public void resume()
    {
        log("resume");
        if (mPauseTimeStamp > 0)
        {
            freezeDurationMillSec += currentTimeMillis() - mPauseTimeStamp;
        }
        mPauseTimeStamp = 0L;
    }

    private boolean isPaused()
    {
        return mPauseTimeStamp > 0;
    }

    // ----------------------------------------------------------------
    // - Inner
    // ----------------------------------------------------------------
    @Override
    public void run()
    {
        log("interval");

        if (runnable != null)
        {
            if (notifyInUIThread)
            {
                mMainHandler.post(runnable);
            }
            else
            {
                runnable.run();
            }
        }
    }

    boolean shouldTrigger()
    {
        return !isPaused() && currentTimeMillis() >= nextTriggerTime + freezeDurationMillSec;
    }

    void resetTriggerTime()
    {
        nextTriggerTime = currentTimeMillis() + scheduleIntervalMillSec;

        freezeDurationMillSec = 0L;

        mPauseTimeStamp = 0L;
    }

    @Override
    public int compareTo(@NonNull ScheduleTask o)
    {
        return (int) (nextTriggerTime - o.nextTriggerTime);
    }

    @Override
    public String toString()
    {
        @SuppressWarnings("StringBufferReplaceableByString")
        StringBuilder builder = new StringBuilder();

        builder.append("[")
                .append(tag)
                .append(",interval=")
                .append(scheduleIntervalMillSec)
                .append("]");

        return builder.toString();
    }

    private long currentTimeMillis()
    {
        return SystemClock.uptimeMillis();
    }

    private void log(String msg)
    {
        if (debugLog)
        {
            Logger.getLogger(TAG).log(Level.INFO, String.format("[%s] %s", tag, msg));
        }
    }

    // ------------------------------------------------------------------------
    // - Builder
    // ------------------------------------------------------------------------
    @SuppressWarnings({"unused", "WeakerAccess", "UnusedReturnValue"})
    public static class Builder
    {
        private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();
        private long delayMillSec = -1L;
        private long scheduleIntervalMillSec = -1L;
        private int pauseCondition = NONE;
        private boolean notifyInUIThread = true;
        private boolean debugLog = false;
        private String tag;
        private Runnable runnable;

        public Builder()
        {
            tag = "TASK#" + ATOMIC_INTEGER.getAndIncrement();
            setDebugLog(false);
            setNotifyInUIThread(true);
        }

        public Builder setTag(String tag)
        {
            this.tag = tag;
            return this;
        }

        public Builder setDelay(long millSec)
        {
            delayMillSec = millSec;
            return this;
        }

        public Builder setScheduleInterval(long millSec)
        {
            scheduleIntervalMillSec = millSec;
            return this;
        }

        public Builder suspendWhenActivityPause(boolean pause)
        {
            pauseCondition = pause ? ACTIVITY_PAUSE : NONE;
            return this;
        }

        public Builder suspendWhenActivityStop(boolean pause)
        {
            pauseCondition = pause ? ACTIVITY_STOP : NONE;
            return this;
        }

        public Builder setRunnable(Runnable runnable)
        {
            this.runnable = runnable;
            return this;
        }

        public Builder setNotifyInUIThread(boolean notifyInUIThread)
        {
            this.notifyInUIThread = notifyInUIThread;
            return this;
        }

        public Builder setDebugLog(boolean debugLog)
        {
            this.debugLog = debugLog;
            return this;
        }

        public ScheduleTask builder()
        {
            return new ScheduleTask(this);
        }
    }
}
