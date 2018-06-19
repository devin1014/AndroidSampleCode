package com.android.liuwei.myandroidcode;

import android.app.Application;
import android.support.annotation.Nullable;

import com.android.liuwei.myandroidcode.cookie.OkHttpClientManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-11
 * Time: 14:31
 */
public class DemoApplication extends Application
{
    @Override
    public void onCreate()
    {
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

        OkHttpClientManager.init(this);
    }
}
