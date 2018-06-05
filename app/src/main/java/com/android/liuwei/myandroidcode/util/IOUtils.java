package com.android.liuwei.myandroidcode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-05-04
 * Time: 16:16
 */
public class IOUtils
{
    public static String parseInputStream(InputStream inputStream) throws IOException
    {
        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String value = reader.readLine();

        while (value != null)
        {
            builder.append(value).append("\n");

            value = reader.readLine();
        }

        return builder.toString();
    }
}
