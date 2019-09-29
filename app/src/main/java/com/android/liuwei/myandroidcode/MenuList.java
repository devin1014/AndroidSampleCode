package com.android.liuwei.myandroidcode;

import android.os.Bundle;

import com.android.liuwei.myandroidcode.feature.architecture.eventbus.EventBusDemoActivity;
import com.android.liuwei.myandroidcode.feature.architecture.lifecycle.LifecycleDemoActivity;
import com.android.liuwei.myandroidcode.feature.http.cookie.CookieActivity;
import com.android.liuwei.myandroidcode.feature.http.lebocast.LeboCastActivity;
import com.android.liuwei.myandroidcode.feature.process.car.CarActivity;
import com.android.liuwei.myandroidcode.feature.thread.handlerthread.WorkThreadActivity;
import com.android.liuwei.myandroidcode.feature.thread.scheduler.SchedulerActivity;
import com.android.liuwei.myandroidcode.feature.ui.databinding.DataBindingDemoActivity;
import com.android.liuwei.myandroidcode.feature.ui.demo.SimpleDemoActivity;
import com.android.liuwei.myandroidcode.feature.ui.deviceinfo.DeviceInfoActivity;
import com.android.liuwei.myandroidcode.feature.ui.dialog.MyDialogActivity;
import com.android.liuwei.myandroidcode.feature.ui.fragment.FragmentActivity;
import com.android.liuwei.myandroidcode.feature.ui.link.LinkActivity;
import com.android.liuwei.myandroidcode.feature.ui.orientation.OrientationActivity;
import com.android.liuwei.myandroidcode.feature.ui.performance.PerformanceActivity;
import com.android.liuwei.myandroidcode.feature.ui.scrollview.ScrollViewHolder;
import com.android.liuwei.myandroidcode.feature.ui.service.ServiceActivity;
import com.android.liuwei.myandroidcode.feature.ui.theme.DayNightDemoActivity;
import com.android.liuwei.myandroidcode.feature.widget.ImageTextView.ImageTextViewActivity;
import com.android.liuwei.myandroidcode.feature.widget.autosizetextview.AutoSizeTextViewActivity;
import com.android.liuwei.myandroidcode.feature.widget.coordinatorlayout.CoordinatorLayoutActivityDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        MENU_LIST.add(new MenuInfo("IPC", CarActivity.class, extra));
        MENU_LIST.add(new MenuInfo("DataBinding", DataBindingDemoActivity.class, extra));
        MENU_LIST.add(new MenuInfo("Lifecycle", LifecycleDemoActivity.class, extra));
        MENU_LIST.add(new MenuInfo("Scheduler", SchedulerActivity.class, extra));
        MENU_LIST.add(new MenuInfo("CoordinatorLayout", CoordinatorLayoutActivityDemo.class, extra));
        MENU_LIST.add(new MenuInfo("WorkThread", WorkThreadActivity.class, extra));
        MENU_LIST.add(new MenuInfo("Performance", PerformanceActivity.class, extra));
        MENU_LIST.add(new MenuInfo("ScrollView", ScrollViewHolder.class, extra));
        MENU_LIST.add(new MenuInfo("ImageTextView", ImageTextViewActivity.class, extra));

        Collections.sort(MENU_LIST, new Comparator<MenuInfo>()
        {
            @Override
            public int compare(MenuInfo o1, MenuInfo o2)
            {
                return o1.name.compareTo(o2.name);
            }
        });
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
