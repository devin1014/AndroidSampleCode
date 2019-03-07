package com.android.liuwei.myandroidcode.base;

import android.util.Log;

/**
 * User: liuwei
 * Date: 2018-04-16
 * Time: 18:07
 */
public class LogUtil
{
    private static final String TAG = "LIUWEI_";

    private static final boolean DEBUG = true;

    public static void log(Object object, String message)
    {
        log(parseObjectTag(object), message);
    }

    private static void log(String tag, String message)
    {
        if (DEBUG)
        {
            Log.d(TAG + tag, message);
        }
    }

    public static void info(Object object, String message)
    {
        info(parseObjectTag(object), message);
    }

    private static void info(String tag, String message)
    {
        if (DEBUG)
        {
            Log.i(TAG + tag, message);
        }
    }

    public static void warn(Object object, String message)
    {
        warn(parseObjectTag(object), message);
    }

    private static void warn(String tag, String message)
    {
        if (DEBUG)
        {
            Log.w(TAG + tag, message);
        }
    }

    public static void error(Object object, String message)
    {
        error(parseObjectTag(object), message);
    }

    private static void error(String tag, String message)
    {
        if (DEBUG)
        {
            Log.e(TAG + tag, message);
        }
    }

    private static String parseObjectTag(Object object)
    {
        if (object == null)
        {
            return "NULL";
        }

        return String.format("%s[%s]", object.getClass().getSimpleName(), Integer.toHexString(object.hashCode()));
    }
}
