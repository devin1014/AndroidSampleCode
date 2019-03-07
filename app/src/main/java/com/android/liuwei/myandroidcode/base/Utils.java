package com.android.liuwei.myandroidcode.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Utils
{
    public static void startActivity(Context context, Class<?> activityClass)
    {
        startActivity(context, activityClass, null);
    }

    public static void startActivity(Context context, Class<?> activityClass, Bundle bundle)
    {
        Intent intent = new Intent(context, activityClass);

        if (bundle != null)
        {
            intent.putExtras(bundle);
        }

        context.startActivity(intent);
    }
}
