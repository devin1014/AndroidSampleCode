package com.android.liuwei.myandroidcode.feature.widget.autosizetextview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.R;

import butterknife.BindView;
import butterknife.OnClick;
import com.android.liuwei.myandroidcode.base.BaseActivity;

public class AutoSizeTextViewActivity extends BaseActivity
{
    @BindView(R.id.edit_text)
    TextInputEditText mTextInputEditText;
    @BindView(R.id.text_show)
    TextView mShowTextView;
    @BindView(R.id.text_size)
    TextView mSizeTextView;

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_autosize_textview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mTextInputEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                mShowTextView.setText(mTextInputEditText.getText().toString());
                mSizeTextView.setText(String.valueOf(mShowTextView.getTextSize()));
            }
        });
    }

    @OnClick(R.id.make_text_larger)
    public void onMakeTextViewLarger()
    {
        ViewGroup.LayoutParams layoutParams = mShowTextView.getLayoutParams();

        layoutParams.width = (int) (mShowTextView.getWidth() * 1.1);

        layoutParams.height = (int) (mShowTextView.getHeight() * 1.1);

        mShowTextView.setLayoutParams(layoutParams);
    }

    @OnClick(R.id.make_text_smaller)
    public void onMakeTextViewSmaller()
    {
        ViewGroup.LayoutParams layoutParams = mShowTextView.getLayoutParams();

        layoutParams.width = (int) (mShowTextView.getWidth() * 0.9);

        layoutParams.height = (int) (mShowTextView.getHeight() * 0.9);

        mShowTextView.setLayoutParams(layoutParams);
    }
}
