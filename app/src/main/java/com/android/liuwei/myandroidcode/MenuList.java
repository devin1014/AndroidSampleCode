package com.android.liuwei.myandroidcode;

import java.util.ArrayList;
import java.util.List;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-04-17
 * Time: 10:30
 */
public class MenuList
{
    public static final String MENU_ORIENTATION="Orientation";

    private static List<String> MENUS = new ArrayList<>();

    static
    {
        MENUS.add(MENU_ORIENTATION);
    }

    public static List<String> getMenus()
    {
        return MENUS;
    }
}
