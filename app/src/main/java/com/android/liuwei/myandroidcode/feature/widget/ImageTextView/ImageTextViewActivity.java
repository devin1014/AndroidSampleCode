package com.android.liuwei.myandroidcode.feature.widget.ImageTextView;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;

public class ImageTextViewActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_image_textview;
    }

    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TextView textView1 = findViewById(R.id.image_text_view);
        Drawable backgroundDrawable = textView1.getBackground();
        backgroundDrawable.setTint(Color.parseColor("#ff0000"));

    }
}
