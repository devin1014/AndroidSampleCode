package com.android.liuwei.myandroidcode.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Utils
{
    public static void startActivity(Context context, Class<?> activityClass)
    {
        startActivity(context, activityClass, null, false);
    }

    public static void startActivity(Context context, Class<?> activityClass, Bundle bundle)
    {
        startActivity(context, activityClass, bundle, false);
    }

    public static void startActivity(Context context, Class<?> activityClass, Bundle bundle, boolean addNewTaskFlag)
    {
        Intent intent = new Intent(context, activityClass);

        if (bundle != null)
        {
            intent.putExtras(bundle);
        }

        if (addNewTaskFlag)
        {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        intent.setData(Uri.parse("liuwei:host/path?query=1"));

        context.startActivity(intent);
    }

    public static SharedPreferences getSharedPreferences(Context context)
    {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static void writeObject2File(Serializable serializable, File file) throws IOException
    {
        ObjectOutputStream outputStream = null;
        try
        {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(serializable);
        }
        finally
        {
            if (outputStream != null)
            {
                outputStream.close();
            }
        }
    }

    public static Serializable readFile2Object(File file) throws IOException, ClassNotFoundException
    {
        ObjectInputStream inputStream = null;
        try
        {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            return (Serializable) inputStream.readObject();
        }
        finally
        {
            if (inputStream != null)
            {
                inputStream.close();
            }
        }
    }
}
