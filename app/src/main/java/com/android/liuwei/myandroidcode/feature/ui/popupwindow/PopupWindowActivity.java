package com.android.liuwei.myandroidcode.feature.ui.popupwindow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;

import com.android.liuwei.myandroidcode.R;

public class PopupWindowActivity extends AppCompatActivity implements OnClickListener
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popup_window);

        findViewById(R.id.popup_window_show_as_dropdown).setOnClickListener(this);

        findViewById(R.id.popup_window_show_at_location).setOnClickListener(this);
    }

    private MyPopupWindow mPopupWindow;

    private void initPopupWindow()
    {
        if (mPopupWindow == null)
        {
            LayoutInflater inflater = LayoutInflater.from(this);

            View inflateView = inflater.inflate(R.layout.comp_popup_window_style1, null, false);

            mPopupWindow = new MyPopupWindow(this);

            mPopupWindow.setWidth(LayoutParams.MATCH_PARENT);

            mPopupWindow.setHeight(LayoutParams.MATCH_PARENT);

            mPopupWindow.setContentView(inflateView);

            mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_rectangle));

            mPopupWindow.setOutsideTouchable(true);
        }
    }

    @Override
    public void onClick(View v)
    {
        initPopupWindow();
        if (v.getId() == R.id.popup_window_show_as_dropdown)
        {
            mPopupWindow.showAsDropDown(v);
        }
        else if (v.getId() == R.id.popup_window_show_at_location)
        {
            mPopupWindow.showAtLocation(v, Gravity.LEFT | Gravity.TOP, 500, 500);
        }
    }
}
