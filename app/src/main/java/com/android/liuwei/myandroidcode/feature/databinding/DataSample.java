package com.android.liuwei.myandroidcode.feature.databinding;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

public class DataSample extends BaseObservable
{
    private ObservableField<String> mTextFiled = new ObservableField<>();

    private ObservableBoolean mLoadingFiled = new ObservableBoolean();

    public DataSample()
    {
        mTextFiled.addOnPropertyChangedCallback(mOnPropertyChangedCallback);
        mLoadingFiled.addOnPropertyChangedCallback(mOnPropertyChangedCallback);
    }

    public String getTextValue()
    {
        return mTextFiled.get();
    }

    public void setTextValue(String text)
    {
        mTextFiled.set(text);
    }

    public boolean isShowLoading()
    {
        return mLoadingFiled.get();
    }

    public void setShowLoading(boolean show)
    {
        mLoadingFiled.set(show);
    }

    @SuppressWarnings("FieldCanBeLocal")
    private OnPropertyChangedCallback mOnPropertyChangedCallback = new OnPropertyChangedCallback()
    {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId)
        {
            notifyChange();
        }
    };
}
