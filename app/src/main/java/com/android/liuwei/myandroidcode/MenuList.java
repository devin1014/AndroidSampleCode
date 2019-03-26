package com.android.liuwei.myandroidcode;

import android.os.Bundle;

import com.android.liuwei.myandroidcode.feature.autosizetextview.AutoSizeTextViewActivity;
import com.android.liuwei.myandroidcode.feature.cookie.CookieActivity;
import com.android.liuwei.myandroidcode.feature.demo.SimpleDemoActivity;
import com.android.liuwei.myandroidcode.feature.deviceinfo.DeviceInfoActivity;
import com.android.liuwei.myandroidcode.feature.dialog.MyDialogActivity;
import com.android.liuwei.myandroidcode.feature.eventbus.EventBusDemoActivity;
import com.android.liuwei.myandroidcode.feature.fragment.FragmentActivity;
import com.android.liuwei.myandroidcode.feature.lebocast.LeboCastActivity;
import com.android.liuwei.myandroidcode.feature.link.LinkActivity;
import com.android.liuwei.myandroidcode.feature.orientation.OrientationActivity;
import com.android.liuwei.myandroidcode.feature.process.LocalAIDLActivity;
import com.android.liuwei.myandroidcode.feature.service.ServiceActivity;
import com.android.liuwei.myandroidcode.feature.theme.DayNightDemoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * User: liuwei
 * Date: 2018-04-17
 * Time: 10:30
 */
public class MenuList
{
    private static List<MenuInfo> MENU_LIST = new ArrayList<>();

    static
    {
        Bundle extra = new Bundle();
        extra.putString("extra_data", "test data");
        extra.putString("extra_data2", "222");

        MENU_LIST.add(new MenuInfo("Orientation", OrientationActivity.class));
        MENU_LIST.add(new MenuInfo("Link", LinkActivity.class));
        MENU_LIST.add(new MenuInfo("Cookie", CookieActivity.class));
        MENU_LIST.add(new MenuInfo("DeviceInfo", DeviceInfoActivity.class));
        MENU_LIST.add(new MenuInfo("Dialog", MyDialogActivity.class));
        MENU_LIST.add(new MenuInfo("LeboCast", LeboCastActivity.class));
        MENU_LIST.add(new MenuInfo("Service", ServiceActivity.class));
        MENU_LIST.add(new MenuInfo("Fragment", FragmentActivity.class));
        MENU_LIST.add(new MenuInfo("EventBus", EventBusDemoActivity.class));
        MENU_LIST.add(new MenuInfo("AutoSize", AutoSizeTextViewActivity.class));
        MENU_LIST.add(new MenuInfo("DayNightTheme", DayNightDemoActivity.class));
        MENU_LIST.add(new MenuInfo("SimpleDemoActivity", SimpleDemoActivity.class));
        MENU_LIST.add(new MenuInfo("IPC", LocalAIDLActivity.class, extra));
    }

    static List<MenuInfo> getMenus()
    {
        return MENU_LIST;
    }

    public static class MenuInfo
    {
        public final String name;
        public final Class<?> clazz;
        public final Bundle extra;

        MenuInfo(String name, Class<?> cls)
        {
            this(name, cls, null);
        }

        MenuInfo(String name, Class<?> cls, Bundle extras)
        {
            this.name = name;
            this.clazz = cls;
            this.extra = extras;
        }
    }
}
