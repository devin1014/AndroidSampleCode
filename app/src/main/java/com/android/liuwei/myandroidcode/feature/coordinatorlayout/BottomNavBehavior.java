package com.android.liuwei.myandroidcode.feature.coordinatorlayout;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

public class BottomNavBehavior extends CoordinatorLayout.Behavior<View>
{
    private float deltaY;

    public BottomNavBehavior()
    {
    }

    public BottomNavBehavior(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency)
    {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency)
    {
        float per = dependency.getY() * 1f / dependency.getHeight();

        child.setTranslationY(-per * child.getHeight());

        return true;
    }
}
