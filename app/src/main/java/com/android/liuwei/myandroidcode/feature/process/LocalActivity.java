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
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;
import com.android.liuwei.myandroidcode.base.LogUtil;

import java.util.Objects;

import butterknife.BindView;

public class LocalActivity extends BaseActivity
{
    @BindView(R.id.input_edit)
    EditText mInputEdit;

    @BindView(R.id.receive_text)
    TextView mReceiveText;

    @BindView(R.id.car_group)
    RadioGroup mCarGroup;

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_local_post;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        bindService(new Intent(this, RemoteService.class), mServiceConnection, Service.BIND_AUTO_CREATE);

        findViewById(R.id.post).setOnClickListener(mOnClickListener);
    }

    @Override
    protected void onDestroy()
    {
        mReceiveHandler.mLocalActivity = null;
        unbindService(mServiceConnection);
        super.onDestroy();
    }

    private Messenger mMessenger;

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

    private ReceiveHandler mReceiveHandler = new ReceiveHandler(this);

    private static class ReceiveHandler extends Handler
    {
        LocalActivity mLocalActivity;

        ReceiveHandler(LocalActivity activity)
        {
            mLocalActivity = activity;
        }

        @Override
        public void handleMessage(Message msg)
        {
            Bundle data = msg.getData();
            data.setClassLoader(getClass().getClassLoader());
            LogUtil.info(this, "msg=" + data.getString("msg"));
            LogUtil.info(this, "car=" + data.getParcelable("car"));
            if (mLocalActivity != null)
            {
                mLocalActivity.mReceiveText.setText(Objects.requireNonNull(data.getParcelable("car")).toString());
            }
        }
    }

    private Messenger mReplyMessenger = new Messenger(mReceiveHandler);

    private OnClickListener mOnClickListener = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            String text = mInputEdit.getText().toString();

            if (mMessenger != null)
            {
                Message msg = Message.obtain();
                msg.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("msg", text);
                bundle.putParcelable("car", getCar());
                bundle.setClassLoader(getClassLoader());
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
    };

    private CarEntity getCar()
    {
        switch (mCarGroup.getCheckedRadioButtonId())
        {
            case R.id.car_bmw:
                return CarEntity.newBMW();
            case R.id.car_benz:
                return CarEntity.newBENZ();
            case R.id.car_audi:
                return CarEntity.newAudi();
            default:
                return null;
        }
    }
}
