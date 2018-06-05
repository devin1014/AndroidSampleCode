package com.android.liuwei.myandroidcode.cookie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.BaseFragment;
import com.android.liuwei.myandroidcode.R;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;

import butterknife.BindView;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-04
 * Time: 14:57
 */
public class CookieFragment extends BaseFragment
{
    @BindView(R.id.cookie_wb_value)
    TextView mWebViewCookie;
    @BindView(R.id.cookie_http_value)
    TextView mHttpCookie;
    private CookieManager sCookieManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container)
    {
        return inflater.inflate(R.layout.fragment_cookie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initComponent();
    }

    private void initComponent()
    {
        {
            // CookieHandler.getDefault() 为空，如果不调用的话
            if (CookieHandler.getDefault() == null)
            {
                if (sCookieManager == null)
                {
                    sCookieManager = new CookieManager();

                    sCookieManager.setCookiePolicy(new CookiePolicy()
                    {
                        @Override
                        public boolean shouldAccept(URI uri, HttpCookie cookie)
                        {
                            //do somethings
                            return true;
                        }
                    });
                }

                CookieHandler.setDefault(sCookieManager);
            }
        }

        //web view
        {
            String cookie = android.webkit.CookieManager.getInstance().getCookie(CookieActivity.URL_VIP_SPORTS);

            StringBuilder builder = new StringBuilder();

            if (!TextUtils.isEmpty(cookie))
            {
                String[] arrays = cookie.split(";");
                for (String s : arrays)
                {
                    builder.append(URLDecoder.decode(s)).append("\n");
                }
            }

            mWebViewCookie.setText(builder.toString());
        }

        //http
        {
            CookieManager cookieManager = (CookieManager) CookieHandler.getDefault();

            CookieStore cookieStore = cookieManager.getCookieStore();

            List<HttpCookie> cookieList = cookieStore.getCookies();

            if (cookieList != null)
            {
                StringBuilder builder = new StringBuilder();
                for (HttpCookie cookie : cookieList)
                {
                    builder.append(cookie.toString()).append("\n");
                }
                mHttpCookie.setText(builder.toString());
            }
        }
    }
}
