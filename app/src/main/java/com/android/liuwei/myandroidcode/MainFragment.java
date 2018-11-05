package com.android.liuwei.myandroidcode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.liuwei.myandroidcode.autosizetextview.AutoSizeTextViewActivity;
import com.android.liuwei.myandroidcode.cookie.CookieActivity;
import com.android.liuwei.myandroidcode.deviceinfo.DeviceInfoActivity;
import com.android.liuwei.myandroidcode.dialog.MyDialogActivity;
import com.android.liuwei.myandroidcode.eventbus.EventBusDemoActivity;
import com.android.liuwei.myandroidcode.fragment.FragmentActivity;
import com.android.liuwei.myandroidcode.lebocast.LeboCastActivity;
import com.android.liuwei.myandroidcode.link.LinkActivity;
import com.android.liuwei.myandroidcode.orientation.OrientationActivity;
import com.android.liuwei.myandroidcode.service.ServiceActivity;

import butterknife.BindView;
import liuwei.android.core.base.BaseFragment;
import liuwei.android.core.widget.ListAdapter;
import liuwei.android.core.widget.ListAdapter.Callback;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment
{
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initComponent();
    }

    private void initComponent()
    {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setAdapter(new ListAdapter(getActivity(), MenuList.getMenus(), mCallback, R.layout.comp_nav_list));
    }

    private ListAdapter.Callback mCallback = new Callback()
    {
        @SuppressWarnings("ConstantConditions")
        @Override
        public void onItemClick(String name)
        {
            switch (name)
            {
                case MenuList.MENU_ORIENTATION:

                    OrientationActivity.startActivity(getActivity(), OrientationActivity.class);

                    break;

                case MenuList.MENU_LINK:

                    LinkActivity.startActivity(getActivity(), LinkActivity.class);

                    break;

                case MenuList.MENU_COOKIE:

                    CookieActivity.startActivity(getActivity(), CookieActivity.class);

                    break;

                case MenuList.MENU_DEVICE_INFO:

                    DeviceInfoActivity.startActivity(getActivity(), DeviceInfoActivity.class);

                    break;

                case MenuList.MENU_DIALOG:

                    MyDialogActivity.startActivity(getActivity(), MyDialogActivity.class);

                    break;

                case MenuList.MENU_LEBOCAST:

                    LeboCastActivity.startActivity(getActivity(), LeboCastActivity.class);

                    break;

                case MenuList.MENU_SERVICE:

                    ServiceActivity.startActivity(getActivity(), ServiceActivity.class);

                    break;

                case MenuList.MENU_FRAGMENT:

                    FragmentActivity.startActivity(getActivity(), FragmentActivity.class);

                    break;

                case MenuList.MENU_EVENTBUS:

                    FragmentActivity.startActivity(getActivity(), EventBusDemoActivity.class);

                    break;

                case MenuList.MENU_AUTOSIZE:

                    FragmentActivity.startActivity(getActivity(), AutoSizeTextViewActivity.class);

                    break;
            }
        }
    };
}
