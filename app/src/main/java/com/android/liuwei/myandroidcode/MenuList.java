package com.android.liuwei.myandroidcode;

import java.util.ArrayList;
import java.util.List;

/**
 * User: liuwei
 * Date: 2018-04-17
 * Time: 10:30
 */
public class MenuList
{
    public static final String MENU_ORIENTATION = "Orientation";
    public static final String MENU_LINK = "Link";
    public static final String MENU_COOKIE = "Cookie";
    public static final String MENU_DEVICE_INFO = "DeviceInfo";
    public static final String MENU_DIALOG = "Dialog";
    public static final String MENU_LEBOCAST = "LeboCast";
    public static final String MENU_SERVICE = "Service";
    public static final String MENU_FRAGMENT = "Fragment";
    public static final String MENU_EVENTBUS = "EventBus";
    public static final String MENU_AUTOSIZE = "AutoSize";
    public static final String MENU_THEME_DAYNIGHT = "DayNightTheme";
    public static final String MENU_SIMPLE_DEMO = "SimpleDemoActivity";

    private static List<String> MENUS = new ArrayList<>();

    static
    {
        MENUS.add(MENU_ORIENTATION);
        MENUS.add(MENU_LINK);
        MENUS.add(MENU_COOKIE);
        MENUS.add(MENU_DEVICE_INFO);
        MENUS.add(MENU_DIALOG);
        MENUS.add(MENU_LEBOCAST);
        MENUS.add(MENU_SERVICE);
        MENUS.add(MENU_FRAGMENT);
        MENUS.add(MENU_EVENTBUS);
        MENUS.add(MENU_AUTOSIZE);
        MENUS.add(MENU_THEME_DAYNIGHT);
        MENUS.add(MENU_SIMPLE_DEMO);
    }

    public static List<String> getMenus()
    {
        return MENUS;
    }
}
