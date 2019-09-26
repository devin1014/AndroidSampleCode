package com.android.liuwei.myandroidcode.base;

import java.util.ArrayList;
import java.util.List;

public class ListProvider
{
    private ListProvider()
    {
    }

    public static List<String> newInstance(int size)
    {
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
        {
            list.add(String.valueOf(i));
        }
        return list;
    }
}
