package com.android.liuwei.myandroidcode.demo;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.android.liuwei.myandroidcode.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import liuwei.android.core.base.BaseActivity;

public class SimpleDemoActivity extends BaseActivity
{
    private String URLS[] = {
            "http://img03.tooopen.com/uploadfile/downs/images/20110714/sy_20110714135215645030.jpg",
            "http://pic1.nipic.com/2008-12-30/200812308231244_2.jpg",
            "http://wx4.sinaimg.cn/large/a1b61d0aly1fn2h3xwat6j20dw0dwtbp.jpg"
    };

    private ImageView mImageView;

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_simple;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mImageView = findViewById(R.id.image);

        HandlerThread handlerThread = new HandlerThread("downloadImage");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), new HandlerCallback());
        for (int i = 0; i < URLS.length; i++)
        {
            handler.sendEmptyMessage(i);
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mUIHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            Log.w(SimpleDemoActivity.this.getClass().getSimpleName()
                    , String.format("handleMessage[%s] [%s]", Thread.currentThread().getName(), msg.what));

            ImageModel model = (ImageModel) msg.obj;
            mImageView.setImageBitmap(model.bitmap);
        }
    };


    class ImageModel
    {
        final Bitmap bitmap;
        final String url;

        ImageModel(String url, Bitmap bitmap)
        {
            this.url = url;
            this.bitmap = bitmap;
        }
    }

    class HandlerCallback implements Handler.Callback
    {
        @Override
        public boolean handleMessage(Message msg)
        {
            Log.i(SimpleDemoActivity.this.getClass().getSimpleName()
                    , String.format("handleMessage[%s] [%s]", Thread.currentThread().getName(), msg.what));

            Bitmap bitmap = downloadUrlBitmap(URLS[msg.what]);
            Message msg1 = Message.obtain();
            msg1.what = msg.what;
            msg1.obj = new ImageModel(URLS[msg.what], bitmap);
            mUIHandler.sendMessage(msg1);
            return false;
        }
    }

    private Bitmap downloadUrlBitmap(String urlString)
    {
        Log.i(SimpleDemoActivity.this.getClass().getSimpleName()
                , String.format("downloadImage [%s]", urlString));

        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap = null;
        try
        {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            bitmap = BitmapFactory.decodeStream(in);
            Thread.sleep(1500L);
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (final IOException e)
            {
                e.printStackTrace();
            }
        }
        Log.i(SimpleDemoActivity.this.getClass().getSimpleName()
                , String.format("downloadImage [%s] [Finish]", urlString));
        return bitmap;
    }
}
