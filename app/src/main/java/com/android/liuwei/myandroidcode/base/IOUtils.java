package com.android.liuwei.myandroidcode.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Response;

/**
 * User: liuwei
 * Date: 2018-05-04
 * Time: 16:16
 */
public class IOUtils
{
    public static String parseURLConnectionResponseHeader(URLConnection connection)
    {
        StringBuilder builder = new StringBuilder();

        Map<String, List<String>> headers = connection.getHeaderFields();

        for (String header : headers.keySet())
        {
            List<String> list = headers.get(header);

            for (String value : list)
            {
                builder.append(header).append(":").append(value).append("\n");
            }
        }

        return builder.toString();
    }

    public static String parseURLConnectionResponse(InputStream inputStream) throws IOException
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

    public static String formatOkHttpResponse(Response response, String string)
    {
        StringBuilder builder = new StringBuilder();

        if (response != null)
        {
            builder.append(response.protocol().toString()).append(" ")
                    .append(response.code()).append(" ")
                    .append(response.message()).append("\n");

            Headers headers = response.headers();

            for (String name : headers.names())
            {
                builder.append(name).append(":").append(headers.get(name)).append("\n");
            }

            builder.append("\n");

            builder.append(string);
        }
        else
        {
            builder.append("response is NULL!");
        }

        return builder.toString();
    }
}
