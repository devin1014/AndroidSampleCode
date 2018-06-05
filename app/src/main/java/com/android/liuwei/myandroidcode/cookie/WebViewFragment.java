package com.android.liuwei.myandroidcode.cookie;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.android.liuwei.myandroidcode.BaseFragment;
import com.android.liuwei.myandroidcode.R;

import butterknife.BindView;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-05-04
 * Time: 16:07
 */
public class WebViewFragment extends BaseFragment
{
    @BindView(R.id.webview)
    WebView mWebView;
    @BindView(R.id.webview_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.webview_loading)
    ProgressBar mLoadingView;

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
        return inflater.inflate(R.layout.fragment_webview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //CookieSyncManager.createInstance(this);
        //CookieSyncManager.getInstance().sync();
        //android.webkit.CookieManager.getInstance().setAcceptCookie(true);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(true);

        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.loadUrl(CookieActivity.URL_VIP_SPORTS);
    }

    @Override
    public void onDestroyView()
    {
        mWebView.destroy();

        super.onDestroyView();
    }

    private class MyWebChromeClient extends WebChromeClient
    {
        @Override
        public void onProgressChanged(WebView view, int newProgress)
        {
            mProgressBar.setProgress(newProgress);
            mProgressBar.setVisibility(newProgress == 100 ? View.GONE : View.VISIBLE);
        }
    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {
            mLoadingView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
            mLoadingView.setVisibility(View.GONE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);

            return true;
        }
    }
}
