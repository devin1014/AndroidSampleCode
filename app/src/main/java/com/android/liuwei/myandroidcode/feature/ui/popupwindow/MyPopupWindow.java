package com.android.liuwei.myandroidcode.feature.ui.popupwindow;

import android.content.Context;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;

public class MyPopupWindow extends PopupWindow
{
    public MyPopupWindow(Context context)
    {
        super(context);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity)
    {
        resetPopupWindowSize(anchor);
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    private void resetPopupWindowSize(View anchor)
    {
        int[] locations = new int[2];

        if (getHeight() == LayoutParams.MATCH_PARENT)
        {
            anchor.getLocationOnScreen(locations);

            int height = anchor.getContext().getResources().getDisplayMetrics().heightPixels - locations[1] - anchor.getMeasuredHeight();

            setHeight(height);
        }
    }
}
