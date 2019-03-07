package com.android.liuwei.myandroidcode.feature.process;

public class RemoteData
{
    private static String DATA = "NO_DATA";

    public static void setData(String data)
    {
        DATA = data;
    }

    public static String getData()
    {
        return DATA;
    }
}
