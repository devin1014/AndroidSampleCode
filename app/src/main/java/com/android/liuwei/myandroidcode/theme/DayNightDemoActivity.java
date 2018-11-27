package com.android.liuwei.myandroidcode.theme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;

import com.android.liuwei.myandroidcode.R;

import butterknife.OnClick;
import liuwei.android.core.base.BaseActivity;

public class DayNightDemoActivity extends BaseActivity
{
    private static int mTheme = 0;

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_daynight;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        if (mTheme != 0)
        {
            setTheme(mTheme);
        }

        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btn_mode_light)
    public void onModeLightClick()
    {
        mTheme = 0;

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        recreate();
    }

    @OnClick(R.id.btn_mode_night)
    public void onModeNightClick()
    {
        mTheme = 0;

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        recreate();
    }

    @OnClick(R.id.btn_theme_light)
    public void onThemeLightClick()
    {
        mTheme = R.style.AppTheme;

        finish();

        startActivity(new Intent(this, DayNightDemoActivity.class));
    }

    @OnClick(R.id.btn_theme_night)
    public void onThemeNightClick()
    {
        mTheme = R.style.AppTheme_Dark;

        finish();

        startActivity(new Intent(this, DayNightDemoActivity.class));
    }

}
