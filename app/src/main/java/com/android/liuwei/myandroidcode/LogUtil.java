package com.android.liuwei.myandroidcode;

import android.util.Log;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-04-16
 * Time: 18:07
 */
public class LogUtil
{
    private static final String TAG = "AndroidCode_";

    private static final boolean DEBUG = true;

    public static void log(String tag, String message)
    {
        if (DEBUG)
        {
            Log.d(TAG + tag, message);
        }
    }

    public static void logInfo(String tag, String message)
    {
        if (DEBUG)
        {
            Log.i(TAG + tag, message);
        }
    }

    public static void logWarn(String tag, String message)
    {
        if (DEBUG)
        {
            Log.w(TAG + tag, message);
        }
    }

    public static void logError(String tag, String message)
    {
        if (DEBUG)
        {
            Log.e(TAG + tag, message);
        }
    }
}
