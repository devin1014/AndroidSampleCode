package com.android.liuwei.myandroidcode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.liuwei.myandroidcode.ListAdapter.Callback;
import com.android.liuwei.myandroidcode.MenuList.MenuInfo;
import com.android.liuwei.myandroidcode.base.BaseFragment;
import com.android.liuwei.myandroidcode.base.Utils;

import butterknife.BindView;

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
        public void onItemClick(MenuInfo menuInfo)
        {
            Utils.startActivity(getActivity(), menuInfo.clazz, menuInfo.extra);
        }
    };
}
