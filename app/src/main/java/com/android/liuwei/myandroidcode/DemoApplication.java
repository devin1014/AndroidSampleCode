package com.android.liuwei.myandroidcode;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import com.android.liuwei.myandroidcode.base.LogUtil;
import com.android.liuwei.myandroidcode.feature.cookie.OkHttpClientManager;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * User: liuwei
 * Date: 2018-06-11
 * Time: 14:31
 */
public class DemoApplication extends Application
{
    @Override
    public void onCreate()
    {
        int pid = android.os.Process.myPid();
        String processName = "NULL";

        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        assert activityManager != null;
        for (RunningAppProcessInfo info : activityManager.getRunningAppProcesses())
        {
            if (info.pid == pid)
            {
                processName = info.processName;
                break;
            }
        }

        LogUtil.info(this, String.format("[pid=%s][%s] application create.", pid, processName));

        super.onCreate();

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(3)
                .tag("LIUWEI_ANDROID_DEMO")
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy)
        {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag)
            {
                return BuildConfig.DEBUG;
            }
        });

        Stetho.initializeWithDefaults(this);

        OkHttpClientManager.init(this);
    }
}
