package com.android.liuwei.myandroidcode.cookie;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
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
public class URLConnectionFragment extends BaseFragment
{
    @BindView(R.id.edit_text)
    AppCompatEditText mEditText;

    @BindView(R.id.url_connection_content)
    TextView mConnectionContent;

    private Handler mHandler = new Handler();
    private ConnectCallback mCallback;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        mCallback = (ConnectCallback) context;
    }

    @Override
    public void onDetach()
    {
        mCallback = null;

        super.onDetach();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container)
    {
        return inflater.inflate(R.layout.fragment_urlconnection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.url_connection_go)
    public void connectUrl()
    {
        final String urlString = mEditText.getText().toString();

        new URLConnectThread(urlString, new URLConnectionCallback()
        {
            @Override
            public void onResult(final String result)
            {
                if (mHandler != null)
                {
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            mConnectionContent.setText(result);

                            mCallback.onConnectComplete(urlString);
                        }
                    });
                }
            }
        }).start();
    }
}
