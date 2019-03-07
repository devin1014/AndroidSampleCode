package com.android.liuwei.myandroidcode.feature.fragment;

import android.content.Context;
import android.os.Build.VERSION_CODES;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.android.liuwei.myandroidcode.base.LogUtil;

/**
 * User: liuwei
 * Date: 2018-08-28
 * Time: 19:07
 */
public class MyFrameLayout extends FrameLayout
{
    public MyFrameLayout(@NonNull Context context)
    {
        super(context);

        LogUtil.info(this, "MyFrameLayout");
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);

        LogUtil.info(this, "MyFrameLayout");
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        LogUtil.info(this, "MyFrameLayout");
    }

    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onAttachedToWindow()
    {
        LogUtil.info(this, "onAttachedToWindow");

        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        LogUtil.info(this, "onMeasure");

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        LogUtil.info(this, "onLayout");

        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDetachedFromWindow()
    {
        LogUtil.info(this, "onDetachedFromWindow");

        super.onDetachedFromWindow();
    }

    @Override
    protected void onFinishInflate()
    {
        LogUtil.info(this, "onFinishInflate");

        super.onFinishInflate();
    }
}
