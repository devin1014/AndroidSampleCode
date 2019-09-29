package com.android.liuwei.myandroidcode.feature.widget.ImageTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class ImageTextView extends android.support.v7.widget.AppCompatTextView
{
    public ImageTextView(Context context)
    {
        super(context);
    }

    public ImageTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ImageTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    private Drawable mLeftDrawable;
    private Drawable mTopDrawable;
    private Drawable mRightDrawable;
    private Drawable mBottomDrawable;

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();

        Drawable[] drawables = getCompoundDrawables();

        if (drawables.length > 0)
        {
            mLeftDrawable = drawables[0];
            mTopDrawable = drawables[1];
            mRightDrawable = drawables[2];
            mBottomDrawable = drawables[3];
        }

        setCompoundDrawables(null, null, null, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST)
        {
            if (mLeftDrawable != null)
            {
                width += mLeftDrawable.getIntrinsicWidth() + getCompoundPaddingLeft();
            }

            if (mRightDrawable != null)
            {
                width += mRightDrawable.getIntrinsicWidth() + getCompoundPaddingRight();
            }
        }

        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST)
        {
            if (mTopDrawable != null)
            {
                height += mTopDrawable.getIntrinsicWidth() + getCompoundPaddingTop();
            }

            if (mBottomDrawable != null)
            {
                height += mBottomDrawable.getIntrinsicWidth() + getCompoundPaddingBottom();
            }
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        if (mLeftDrawable != null)
        {
            initDrawableRectIfNeeded(mLeftDrawable);
            canvas.save();
            canvas.translate(mLeftDrawable.getIntrinsicWidth() + getCompoundPaddingLeft(), 0);
            super.onDraw(canvas);
            canvas.translate(-(mLeftDrawable.getIntrinsicWidth() + getCompoundPaddingLeft()), 0);
            canvas.translate(getPaddingLeft(), 0);
            mLeftDrawable.draw(canvas);
            canvas.translate(-getPaddingLeft(), 0);
            canvas.restore();

        }
    }

    private void initDrawableRectIfNeeded(Drawable drawable)
    {
        Rect bounds = drawable.getBounds();
        if (bounds.width() == 0 || bounds.height() == 0)
        {
            bounds.set(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }
}
