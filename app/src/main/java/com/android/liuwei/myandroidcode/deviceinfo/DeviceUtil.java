/*
 */
package com.android.liuwei.myandroidcode.deviceinfo;

import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Locale;
import java.util.regex.Pattern;

import liuwei.android.core.util.LogUtil;


@SuppressWarnings("UnusedDeclaration")
public class DeviceUtil
{
    public static final String DEVICE_PHONE = "android mobile";
    public static final String DEVICE_TABLET = "android";
    public static final String DEVICE_ANDROIDTV = "androidtv";
    public static final String DEVICE_KINDLE = "kindle";
    public static final String DEVICE_FIRETV = "firetv";

    public static final String TY_ANDROID = "8";
    public static final String TY_ANDROIDTAB = "13";
    public static final String TY_KINDLE = "14";

    public static final String TY_FIRETV = "142";
    public static final String TY_ANDROIDTV = "143";

    public static String getNLDeviceLinkType(Context context)
    {
        if (isAmazon())
        {
            return isTVDevice(context) ? TY_FIRETV : TY_KINDLE;
        }
        else
        {
            if (isTVDevice(context))
            {
                return TY_ANDROIDTV;
            }
            else
            {
                return TY_ANDROID;
            }
        }
    }


    public static String getNLUserAgent(Context appContext)
    {
        final StringBuilder sb = new StringBuilder();

        String defaultUA = System.getProperty("http.agent");

        if (!checkUserAgent(defaultUA))
        {
            defaultUA = "Android";
        }

        sb.append(defaultUA);

        sb.append(" ");
        sb.append(getNLDeviceType(appContext));

        final String appName = getAppName(appContext);
        if (checkUserAgent(appName))
        {
            // app name
            sb.append(" ");
            sb.append(appName.toLowerCase(Locale.US));
        }

        final String versionName = getAppVersionName(appContext);
        if (checkUserAgent(versionName))
        {
            // app version name
            sb.append(" ");
            sb.append(getAppVersionName(appContext));
        }

        return sb.toString();
    }

    public static boolean isAmazon()
    {
        return TextUtils.equals(Build.MANUFACTURER, "Amazon");
    }

    public static boolean isTablet(Context context)
    {
        if (isTVDevice(context))
        {
            return false;
        }

        if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB_MR2)
        {
            return context.getResources().getConfiguration().smallestScreenWidthDp >= 600;
        }
        else if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isTVDevice(Context context)
    {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService(Context.UI_MODE_SERVICE);

        return uiModeManager.getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION;
    }

    public static String getNLDeviceType(Context context)
    {
        if (isAmazon())
        {
            return isTVDevice(context) ? DEVICE_FIRETV : DEVICE_KINDLE;
        }
        else
        {
            if (isTVDevice(context))
            {
                return DEVICE_ANDROIDTV;
            }
            else
            {
                return isTablet(context) ? DEVICE_TABLET : DEVICE_PHONE;
            }
        }
    }

    private static boolean checkUserAgent(String ua)
    {
        if (TextUtils.isEmpty(ua))
        {
            return false;
        }

        for (int i = 0, length = ua.length(); i < length; i++)
        {
            char c = ua.charAt(i);
            if (c <= '\u001f' || c >= '\u007f')
            {
                return false;
            }
        }

        return true;
    }


    @SuppressLint({"NewApi", "MissingPermission"})
    public static String getDeviceId(Context context)
    {
        String result;

        // get imei/imsi
        try
        {
            final Pattern p_number = Pattern.compile("\\d+");
            final Pattern p_zeros = Pattern.compile("[0]+");
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager != null)
            {
                result = telephonyManager.getDeviceId();
                if (!TextUtils.isEmpty(result) && p_number.matcher(result).matches() && !p_zeros.matcher(result).matches())
                {
                    return result;
                }

                result = telephonyManager.getSubscriberId();
                if (!TextUtils.isEmpty(result))
                {
                    return result;
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();

            LogUtil.warn(DeviceUtil.class.getSimpleName(), e.toString());
        }

        // get serial number
        try
        {
            if (VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD)
            {
                result = Build.SERIAL;
            }
            else
            {
                Class<?> c = Class.forName("android.os.SystemProperties");
                Method get = c.getMethod("get", String.class, String.class);
                result = (String) (get.invoke(c, "ro.serialno", "unknown"));
            }

            if (!TextUtils.isEmpty(result))
            {
                return result;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();

            LogUtil.warn(DeviceUtil.class.getSimpleName(), e.toString());
        }

        // get Android ID
        try
        {
            result = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

            if (!TextUtils.isEmpty(result))
            {
                return result;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();

            LogUtil.warn(DeviceUtil.class.getSimpleName(), e.toString());
        }

        return null;
    }

    public static String getAppVersionName(Context context)
    {
        String versionStr = "UNKNOWN";
        try
        {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            PackageInfo packageInfo;

            packageInfo = packageManager.getPackageInfo(packageName, 0);

            versionStr = packageInfo.versionName;
        }
        catch (Exception e)
        {
            e.printStackTrace();

            LogUtil.warn(DeviceUtil.class.getSimpleName(), e.toString());
        }
        return versionStr;
    }

    public static int getAppVersionCode(Context context)
    {
        int versionCode = -1;
        try
        {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            PackageInfo packageInfo;

            packageInfo = packageManager.getPackageInfo(packageName, 0);

            versionCode = packageInfo.versionCode;
        }
        catch (Exception e)
        {
            e.printStackTrace();

            LogUtil.warn(DeviceUtil.class.getSimpleName(), e.toString());
        }
        return versionCode;
    }

    public static String getAppName(Context context)
    {
        String appName = null;

        try
        {
            appName = context.getResources().getString(context.getApplicationInfo().labelRes);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            LogUtil.warn(DeviceUtil.class.getSimpleName(), e.toString());

        }

        return appName;
    }

    private static final String NETWORK_WIFI = "WiFi";
    private static final String NETWORK_CARRIER = "Carrier";

    public static String getNetworkType(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetInfo != null)
        {
            final int type = activeNetInfo.getType();

            if (type == ConnectivityManager.TYPE_WIFI)
            {
                return NETWORK_WIFI;
            }
            else if (type == ConnectivityManager.TYPE_MOBILE)
            {
                return NETWORK_CARRIER;
            }
        }

        return NETWORK_CARRIER;
    }

    public static String getDeviceType()
    {
        return Build.MODEL + "_" + Build.MANUFACTURER;
    }

    // ----------------------software-----------------------

    public static String getOS()
    {
        return "android_" + VERSION.RELEASE;
    }

    public static String getMacString(Context context)
    {
        String networkType = "";

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null)
        {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null)
            {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
                {
                    networkType = "wlan0";
                }
                else if (networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET)
                {
                    networkType = "eth0";
                }
                else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
                {
                    networkType = "wlan0";
                }
            }
        }

        try
        {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements())
            {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if (!TextUtils.isEmpty(networkType) && !networkInterface.getName().equalsIgnoreCase(networkType))
                {
                    continue;
                }
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress == null || hardwareAddress.length == 0)
                {
                    continue;
                }
                StringBuilder stringBuffer = new StringBuilder();
                for (byte hardwareAddres : hardwareAddress)
                {
                    stringBuffer.append(String.format("%02X:", hardwareAddres));
                }
                if (stringBuffer.length() > 0)
                {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }
                Log.w("network_mac_info", networkInterface.getName() + ":" + stringBuffer.toString());
                return stringBuffer.toString();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();

            LogUtil.warn(DeviceUtil.class.getSimpleName(), e.toString());
        }
        return null;
    }
}
