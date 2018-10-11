package com.android.liuwei.myandroidcode.deviceinfo;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * User: liuwei
 * Date: 2018-04-26
 * Time: 14:55
 */
public class DeviceInfoHelper
{
    private static final Uri CONTENT_URI = Uri.parse("content://com.tcl.xian.StartandroidService.MyContentProvider/devicetoken");
    private static final String[] COLUMNS = new String[]{"dum", "devicemodel"};

    public static DeviceInfo getDeviceInfo(Context context)
    {
        Cursor cur = context.getContentResolver().query(CONTENT_URI, COLUMNS, null, null, null);
        if (cur != null)
        {
            if (cur.moveToFirst())
            {
                DeviceInfo deviceInfo = new DeviceInfo();
                deviceInfo.dnum = cur.getString(cur.getColumnIndex(COLUMNS[0]));
                deviceInfo.clientType = cur.getString(cur.getColumnIndex(COLUMNS[1]));
                return deviceInfo;
            }
            cur.close();
        }
        return null;
    }

    public static class DeviceInfo
    {
        String dnum;
        String clientType;

        public String getDnum()
        {
            return dnum;
        }

        public String getClientType()
        {
            return clientType;
        }

        @Override
        public String toString()
        {
            return "{" + "dnum:" + dnum + ",clientType:" + clientType + "}";
        }
    }
}