package com.android.liuwei.myandroidcode.cookie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BasePageFragment;
import com.android.liuwei.myandroidcode.cookie.BaseHttpThread.HttpCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-05-04
 * Time: 16:01
 */
public class HttpFragment extends BasePageFragment
{
    @BindView(R.id.label_http_response)
    TextView mHttpResponse;

    public static String mTicket = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saveInstance)
    {
        return inflater.inflate(R.layout.fragment_http, container, false);
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

        new LogInUserNameThread(CookieConstant.URL_SIGNIN_USERNAME, mHttpCallback).start();
    }

    @OnClick(R.id.btn_login_ticket)
    public void loginTicket()
    {
        clearResponseValue();

        new LogInTicketThread(CookieConstant.URL_SIGNIN_TICKET, mTicket, mHttpCallback).start();
    }

    @OnClick(R.id.btn_account_thumbnail)
    public void getAccountThumbnail()
    {
        clearResponseValue();

        new AccountThumbnail(CookieConstant.URL_ACCOUNT_THUMBNAIL, mHttpCallback).start();
    }

    @OnClick(R.id.btn_check_session)
    public void sessionCheck()
    {
        clearResponseValue();

        //        new URLConnectThread(url, mHttpCallback).start();
        new CheckSessionThread(CookieConstant.URL_CHECK_SESSION, mHttpCallback).start();
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
