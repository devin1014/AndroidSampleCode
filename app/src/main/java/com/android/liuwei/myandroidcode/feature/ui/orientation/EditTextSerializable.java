package com.android.liuwei.myandroidcode.feature.ui.orientation;

import java.io.Serializable;

public class EditTextSerializable implements Serializable
{
    private static final long serialVersionUID = 129553663592446805L;

    public final String editTextValue;

    public EditTextSerializable(String value)
    {
        editTextValue = value;
    }
}
