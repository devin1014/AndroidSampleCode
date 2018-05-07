package com.android.liuwei.myandroidcode.cookie;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
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
    @BindView(R.id.edit_text)
    AppCompatEditText mEditText;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

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
        return inflater.inflate(R.layout.fragment_webview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.loadUrl(mEditText.getText().toString());
    }

    private class MyWebChromeClient extends WebChromeClient
    {
        @Override
        public void onProgressChanged(WebView view, int newProgress)
        {
            mProgressBar.setProgress(newProgress);
        }
    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
            mProgressBar.setVisibility(View.GONE);

            if (url.equalsIgnoreCase(mEditText.getText().toString()))
            {
                mCallback.onConnectComplete(url);
            }
        }
    }
}
