package com.android.liuwei.myandroidcode.feature.process;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;
import com.android.liuwei.myandroidcode.base.LogUtil;

public class LocalActivity extends BaseActivity
{
    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_local_post;
    }

    private TextView mReceiveText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mReceiveText = findViewById(R.id.receive_text);

        bindService(new Intent(this, RemoteService.class), mServiceConnection, Service.BIND_AUTO_CREATE);

        final EditText editText = findViewById(R.id.input_edit);

        findViewById(R.id.post).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String text = editText.getText().toString();

                if (mMessenger != null)
                {
                    Message msg = Message.obtain();
                    msg.what = 1;
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", text);
                    msg.setData(bundle);
                    msg.replyTo = mReplyMessenger;
                    try
                    {
                        mMessenger.send(msg);
                    }
                    catch (RemoteException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        unbindService(mServiceConnection);
        super.onDestroy();
    }

    private Messenger mMessenger;

    private Messenger mReplyMessenger = new Messenger(new ReceiveHandler());

    private ServiceConnection mServiceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            LogUtil.info(this, String.format("onServiceConnected(%s)", name.getShortClassName()));
            mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            LogUtil.warn(this, String.format("onServiceDisconnected(%s)", name.getShortClassName()));
        }
    };

    private static class ReceiveHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);

            LogUtil.info(this, "msg=" + msg.getData().getString("msg"));
        }
    }
}
