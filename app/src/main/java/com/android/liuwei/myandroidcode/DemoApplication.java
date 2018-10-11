package com.android.liuwei.myandroidcode;

import android.app.Application;
import android.support.annotation.Nullable;

import com.android.liuwei.myandroidcode.cookie.OkHttpClientManager;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import liuwei.android.core.util.LogUtil;

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
        LogUtil.info(this, "application create!!!");

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

    @Override
    public void onTerminate()
    {
        LogUtil.warn(this, "onTerminate");

        super.onTerminate();
    }

    @Override
    public void onTrimMemory(int level)
    {
        LogUtil.warn(this, "onTrimMemory:" + level);

        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory()
    {
        LogUtil.warn(this, "onLowMemory");

        super.onLowMemory();
    }
}
