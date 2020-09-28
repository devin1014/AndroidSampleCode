package com.android.liuwei.myandroidcode.feature.ui.viewpager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseFragment;
import com.android.liuwei.myandroidcode.base.LogUtil;
import com.android.liuwei.myandroidcode.feature.widget.viewpager.FragmentPersistPageAdapter;

public class ViewPagerDemoActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
    }

    private static class MyFragmentAdapter extends FragmentPersistPageAdapter
    {
        MyFragmentAdapter(FragmentManager manager)
        {
            super(manager);
        }

        @Override
        public Fragment getItem(int position)
        {
            MyPageFragment fragment = new MyPageFragment();
            fragment.setPosition(position);
            return fragment;
        }

        @Override
        public int getCount()
        {
            return 3;
        }
    }

    public static class MyPageFragment extends BaseFragment
    {
        private static String FORMAT = "[%s] %s";
        private int mPosition = -1;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
        {
            return inflater.inflate(R.layout.fragment_my_page, container, false);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
        {
            LogUtil.info(this, String.format(FORMAT, mPosition, "onViewCreated"));
            super.onViewCreated(view, savedInstanceState);

            TextView name = view.findViewById(R.id.name);
            name.setText("Position:" + mPosition);
        }

        public void setPosition(int position)
        {
            mPosition = position;
        }

        @Override
        public void onResume()
        {
            super.onResume();
            LogUtil.info(this, String.format(FORMAT, mPosition, "onResume"));
        }

        @Override
        public void onPause()
        {
            super.onPause();
            LogUtil.info(this, String.format(FORMAT, mPosition, "onPause"));
        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser)
        {
            super.setUserVisibleHint(isVisibleToUser);
            LogUtil.info(this, String.format(FORMAT, mPosition, "setUserVisibleHint:" + isVisibleToUser));
        }

        @Override
        public void onHiddenChanged(boolean hidden)
        {
            super.onHiddenChanged(hidden);
            LogUtil.info(this, String.format(FORMAT, mPosition, "onHiddenChanged:" + hidden));
        }
    }
}
