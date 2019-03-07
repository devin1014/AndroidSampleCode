package com.android.liuwei.myandroidcode.feature.cookie;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * User: liuwei
 * Date: 2018-06-12
 * Time: 15:00
 */
public class PersistentCookieStore implements OkHttpCookieStore, CookieJar
{
    private static final String LOG_TAG = "PersistentCookieStore";
    private static final String COOKIE_PREFS = "Cookies_Prefs";

    private final Map<String, ConcurrentHashMap<String, Cookie>> mCookiesMap;
    private final SharedPreferences mSharedPreferences;

    public PersistentCookieStore(Context context)
    {
        mCookiesMap = new HashMap<>();

        mSharedPreferences = context.getSharedPreferences(COOKIE_PREFS, Context.MODE_PRIVATE);

        Map<String, ?> prefsMap = mSharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : prefsMap.entrySet())
        {
            String[] cookieNames = TextUtils.split((String) entry.getValue(), ",");

            for (String name : cookieNames)
            {
                String cookie = mSharedPreferences.getString(name, null);

                if (cookie != null)
                {
                    Cookie decodedCookie = decodeCookie(cookie);

                    if (decodedCookie != null)
                    {
                        if (!mCookiesMap.containsKey(entry.getKey()))
                        {
                            mCookiesMap.put(entry.getKey(), new ConcurrentHashMap<String, Cookie>());
                        }

                        mCookiesMap.get(entry.getKey()).put(name, decodedCookie);
                    }
                }
            }
        }
    }

    @Override
    public void saveFromResponse(@NonNull HttpUrl url, @NonNull List<Cookie> cookies)
    {
        for (Cookie cookie : cookies)
        {
            addCookie(url.uri(), cookie);
        }
    }

    @Override
    public List<Cookie> loadForRequest(@NonNull HttpUrl url)
    {
        List<Cookie> cookies = getCookie(url.uri());

        if (cookies == null)
        {
            cookies = Collections.emptyList();
        }

        return cookies;
    }

    public void addCookie(URI uri, Cookie cookie)
    {
        //TODO: cookie 和 uri 跨域的问题！

        String host = uri.getHost();

        String name = getCookieToken(cookie);

        //将cookies缓存到内存中 如果缓存过期 就重置此cookie
        if (!cookie.persistent())
        {
            if (!mCookiesMap.containsKey(host))
            {
                mCookiesMap.put(host, new ConcurrentHashMap<String, Cookie>());
            }

            mCookiesMap.get(host).put(name, cookie);
        }
        else
        {
            if (mCookiesMap.containsKey(host))
            {
                mCookiesMap.get(host).remove(name);
            }
        }

        //讲cookies持久化到本地
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(host, TextUtils.join(",", mCookiesMap.get(host).keySet()));
        editor.putString(name, encodeCookie(new SerializableCookies(cookie)));
        editor.apply();
    }

    public List<Cookie> getCookie(URI uri)
    {
        return getCookie(uri.getHost());
    }

    public List<Cookie> getCookie(String host)
    {
        ArrayList<Cookie> list = new ArrayList<>();

        if (mCookiesMap.containsKey(host))
        {
            list.addAll(mCookiesMap.get(host).values());
        }

        return list;
    }

    public List<Cookie> getAllCookie()
    {
        ArrayList<Cookie> list = new ArrayList<>();

        for (String key : mCookiesMap.keySet())
        {
            list.addAll(mCookiesMap.get(key).values());
        }

        return list;
    }

    public Set<String> getCookieDomainUrl()
    {
        return mCookiesMap.keySet();
    }

    private String getCookieToken(Cookie cookie)
    {
        return cookie.name() + "@" + cookie.domain();
    }

    public void removeAllCookie()
    {
        mSharedPreferences.edit().clear().apply();

        mCookiesMap.clear();
    }

    public void removeCookie(URI uri, Cookie cookie)
    {
        String host = uri.getHost();

        String name = getCookieToken(cookie);

        if (mCookiesMap.containsKey(host) && mCookiesMap.get(host).containsKey(name))
        {
            mCookiesMap.get(host).remove(name);

            SharedPreferences.Editor editor = mSharedPreferences.edit();

            if (mSharedPreferences.contains(name))
            {
                editor.remove(name);
            }

            editor.putString(host, TextUtils.join(",", mCookiesMap.get(host).keySet()));

            editor.apply();
        }
    }

    /**
     * mCookiesMap 序列化成 string
     *
     * @param cookie 要序列化的cookie
     * @return 序列化之后的string
     */
    private String encodeCookie(SerializableCookies cookie)
    {
        if (cookie == null)
        {
            return null;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            objectOutputStream.writeObject(cookie);
        }
        catch (IOException e)
        {
            Log.d(LOG_TAG, "IOException in encodeCookie", e);

            return null;
        }

        return byteArrayToHexString(byteArrayOutputStream.toByteArray());
    }

    /**
     * 将字符串反序列化成cookies
     *
     * @param cookieString mCookiesMap string
     * @return cookie object
     */
    private Cookie decodeCookie(String cookieString)
    {
        byte[] bytes = hexStringToByteArray(cookieString);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        Cookie cookie = null;

        try
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            cookie = ((SerializableCookies) objectInputStream.readObject()).getCookies();
        }
        catch (IOException e)
        {
            Log.d(LOG_TAG, "IOException in decodeCookie", e);
        }
        catch (ClassNotFoundException e)
        {
            Log.d(LOG_TAG, "ClassNotFoundException in decodeCookie", e);
        }

        return cookie;
    }

    /**
     * 二进制数组转十六进制字符串
     *
     * @param bytes byte array to be converted
     * @return string containing hex values
     */
    private String byteArrayToHexString(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder(bytes.length * 2);

        for (byte element : bytes)
        {
            int v = element & 0xff;

            if (v < 16)
            {
                sb.append('0');
            }

            sb.append(Integer.toHexString(v));
        }

        return sb.toString().toUpperCase(Locale.US);
    }

    /**
     * 十六进制字符串转二进制数组
     *
     * @param hexString string of hex-encoded values
     * @return decoded byte array
     */
    private byte[] hexStringToByteArray(String hexString)
    {
        int len = hexString.length();

        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2)
        {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }

        return data;
    }

}
