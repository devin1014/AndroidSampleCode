package com.android.liuwei.myandroidcode.feature.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;

/**
 * User: liuwei
 * Date: 2018-08-28
 * Time: 18:40
 */
public class FragmentActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        findViewById(R.id.btn_add_fragment_a).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_remove_fragment_a).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_show_fragment_a).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_hide_fragment_a).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_add_fragment_b).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_remove_fragment_b).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_show_fragment_b).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_hide_fragment_b).setOnClickListener(mOnClickListener);
    }

    private Fragment mFragmentA;
    private Fragment mFragmentB;

    private OnClickListener mOnClickListener = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_fragment);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            switch (v.getId())
            {
                case R.id.btn_add_fragment_a:
                    transaction.add(R.id.container_fragment, mFragmentA = new FragmentA());
                    break;
                case R.id.btn_add_fragment_b:
                    transaction.add(R.id.container_fragment, mFragmentB = new FragmentB());
                    break;
                case R.id.btn_remove_fragment_a:
                case R.id.btn_remove_fragment_b:
                    transaction.remove(fragment);
                    if (fragment instanceof FragmentA)
                    {
                        mFragmentA = null;
                    }
                    else if (fragment instanceof FragmentB)
                    {
                        mFragmentB = null;
                    }
                    break;
                case R.id.btn_show_fragment_a:
                    if (mFragmentB != null)
                    {
                        transaction.hide(mFragmentB);
                    }
                    if (mFragmentA != null)
                    {
                        transaction.show(mFragmentA);
                    }
                    break;
                case R.id.btn_show_fragment_b:
                    if (mFragmentA != null)
                    {
                        transaction.hide(mFragmentA);
                    }
                    if (mFragmentB != null)
                    {
                        transaction.show(mFragmentB);
                    }
                    break;
                case R.id.btn_hide_fragment_a:
                    if (mFragmentA != null)
                    {
                        transaction.hide(mFragmentA);
                    }
                    break;
                case R.id.btn_hide_fragment_b:
                    if (mFragmentB != null)
                    {
                        transaction.hide(mFragmentB);
                    }
                    break;
            }

            transaction.commit();
        }
    };
}
