package com.android.liuwei.myandroidcode.feature.databinding;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

public class DataSample extends BaseObservable
{
    private ObservableField<String> mTextFiled = new ObservableField<>();

    private ObservableBoolean mLoadingFiled = new ObservableBoolean();

    private ObservableBoolean mSwitchOn = new ObservableBoolean(true);

    public DataSample()
    {
        mTextFiled.addOnPropertyChangedCallback(mOnPropertyChangedCallback);
        mLoadingFiled.addOnPropertyChangedCallback(mOnPropertyChangedCallback);
        mSwitchOn.addOnPropertyChangedCallback(mOnPropertyChangedCallback);
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

    public boolean isSwitchOn()
    {
        return mSwitchOn.get();
    }

    public void setSwitch(boolean on)
    {
        mSwitchOn.set(on);
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
