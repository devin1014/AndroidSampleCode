package com.android.liuwei.myandroidcode.feature.ui.orientation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;
import com.android.liuwei.myandroidcode.base.Utils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;

/**
 * User: liuwei
 * Date: 2018-04-17
 * Time: 10:40
 */
public class OrientationActivity extends BaseActivity
{
    private static final String FILE_NAME = "test.txt";

    @BindView(R.id.edit_text)
    EditText mEditText;

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_orientation;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (Utils.getSharedPreferences(this).getBoolean("hasExtra", false))
        {
            try
            {
                EditTextSerializable editObj = (EditTextSerializable) Utils.readFile2Object(new File(getCacheDir(), FILE_NAME));

                mEditText.setText(editObj.editTextValue);
            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        String value = mEditText.getText().toString();
        outState.putString("editText", value);
        super.onSaveInstanceState(outState);
        Utils.getSharedPreferences(this).edit().putBoolean("hasExtra", true).apply();
        EditTextSerializable editObj = new EditTextSerializable(value);
        try
        {
            Utils.writeObject2File(editObj, new File(getCacheDir(), FILE_NAME));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Utils.getSharedPreferences(this).edit().putBoolean("hasExtra", false).apply();
    }
}
