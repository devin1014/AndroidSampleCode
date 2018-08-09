package com.android.liuwei.myandroidcode.cookie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.cookie.thread.BaseHttpThread.HttpCallback;
import com.android.liuwei.myandroidcode.cookie.thread.CheckSessionConnectThread;
import com.android.liuwei.myandroidcode.cookie.thread.CheckSessionThread;
import com.android.liuwei.myandroidcode.cookie.thread.LogInTicketConnectThread;
import com.android.liuwei.myandroidcode.cookie.thread.LogInTicketThread;
import com.android.liuwei.myandroidcode.cookie.thread.LogInUserNameConnectThread;
import com.android.liuwei.myandroidcode.cookie.thread.LogInUserNameThread;
import com.android.liuwei.myandroidcode.cookie.thread.UserThumbNailConnectThread;
import com.android.liuwei.myandroidcode.cookie.thread.UserThumbNailThread;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import liuwei.android.core.base.BasePageFragment;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-05-04
 * Time: 16:01
 */
public class HttpRequestFragment extends BasePageFragment
{
    public static String mTicket = null;

    @BindView(R.id.label_http_response)
    TextView mHttpResponse;

    private boolean mRequestByOkHttp = true;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saveInstance)
    {
        return inflater.inflate(R.layout.fragment_http_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.btn_login)
    public void logInUserName()
    {
        clearResponseValue();

        if (mRequestByOkHttp)
        {
            new LogInUserNameThread(CookieConstant.URL_SIGNIN_USERNAME, mHttpCallback).start();
        }
        else
        {
            new LogInUserNameConnectThread(CookieConstant.URL_SIGNIN_USERNAME, mHttpCallback).start();
        }
    }

    @OnClick(R.id.btn_login_ticket)
    public void loginTicket()
    {
        clearResponseValue();

        if (mRequestByOkHttp)
        {
            new LogInTicketThread(CookieConstant.URL_SIGNIN_TICKET, mTicket, mHttpCallback).start();
        }
        else
        {
            new LogInTicketConnectThread(CookieConstant.URL_SIGNIN_TICKET, mTicket, mHttpCallback).start();
        }
    }

    @OnClick(R.id.btn_check_session)
    public void sessionCheck()
    {
        clearResponseValue();

        if (mRequestByOkHttp)
        {
            new CheckSessionThread(CookieConstant.URL_CHECK_SESSION, mHttpCallback).start();
        }
        else
        {
            new CheckSessionConnectThread(CookieConstant.URL_CHECK_SESSION, mHttpCallback).start();
        }
    }

    @OnClick(R.id.btn_account_thumbnail)
    public void getAccountThumbnail()
    {
        clearResponseValue();

        if (mRequestByOkHttp)
        {
            new UserThumbNailThread(CookieConstant.URL_ACCOUNT_THUMBNAIL, mHttpCallback).start();
        }
        else
        {
            new UserThumbNailConnectThread(CookieConstant.URL_ACCOUNT_THUMBNAIL, mHttpCallback).start();
        }
    }

    @OnCheckedChanged(R.id.radio_request_okhttp)
    public void onRequestOkHttpChecked(boolean checked)
    {
        mRequestByOkHttp = checked;
    }

    @OnCheckedChanged(R.id.radio_request_urlconnection)
    public void onRequestURLConnectionChecked(boolean checked)
    {
        mRequestByOkHttp = !checked;
    }

    @Override
    public void onUnselected()
    {
        super.onUnselected();

        CookieSyncManagerCompat.getInstance().sync();
    }

    private void clearResponseValue()
    {
        if (mHttpResponse != null)
        {
            mHttpResponse.setText("");
        }
    }

    private HttpCallback mHttpCallback = new HttpCallback()
    {
        @Override
        public void onResult(String result)
        {
            if (mHttpResponse != null)
            {
                mHttpResponse.setText(result);
            }
        }
    };
}
