package com.android.liuwei.myandroidcode.cookie;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.BaseFragment;
import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.cookie.URLConnectThread.URLConnectionCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-05-04
 * Time: 16:01
 */
public class HttpFragment extends BaseFragment
{
    @BindView(R.id.http_editor)
    TextInputEditText mEditText;
    @BindView(R.id.http_response)
    TextView mHttpResponse;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container)
    {
        return inflater.inflate(R.layout.fragment_http, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mEditText.setText(CookieActivity.URL_CHECK_SESSION);
    }

    @OnClick(R.id.http_request)
    public void request()
    {
        final String urlString = mEditText.getText().toString();

        new URLConnectThread(urlString, new URLConnectionCallback()
        {
            @Override
            public void onResult(final String result)
            {
                if (mHttpResponse != null)
                {
                    mHttpResponse.setText(result);
                }
            }
        }).start();
    }
}
