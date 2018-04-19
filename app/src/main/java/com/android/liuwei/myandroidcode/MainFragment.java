package com.android.liuwei.myandroidcode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.liuwei.myandroidcode.activity.OrientationActivity;
import com.android.liuwei.myandroidcode.widget.ListAdapter;
import com.android.liuwei.myandroidcode.widget.ListAdapter.Callback;

import butterknife.BindView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment
{
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container)
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

        mRecyclerView.setAdapter(new ListAdapter(getActivity(), MenuList.getMenus(), mCallback));
    }

    private ListAdapter.Callback mCallback = new Callback()
    {
        @Override
        public void onItemClick(String name)
        {
            switch (name)
            {
                case MenuList.MENU_ORIENTATION:

                    OrientationActivity.startActivity(getActivity());

                    break;
            }
        }
    };
}
