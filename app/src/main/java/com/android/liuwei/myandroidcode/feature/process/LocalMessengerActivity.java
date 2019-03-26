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
import android.widget.Toast;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;
import com.android.liuwei.myandroidcode.base.LogUtil;

import java.util.Objects;

import butterknife.BindView;

public class LocalMessengerActivity extends BaseActivity
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

        bindService(new Intent(this, RemoteMessengerService.class), mServiceConnection, Service.BIND_AUTO_CREATE);

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
        LocalMessengerActivity mLocalActivity;

        ReceiveHandler(LocalMessengerActivity activity)
        {
            mLocalActivity = activity;
        }

        @Override
        public void handleMessage(Message msg)
        {
            Bundle data = msg.getData();
            data.setClassLoader(getClass().getClassLoader());
            float discount = data.getFloat("msg");
            Car car = data.getParcelable("car");
            LogUtil.info(this, "msg=" + discount);
            LogUtil.info(this, "car=" + car);
            if (mLocalActivity != null && car != null)
            {
                mLocalActivity.mReceiveText.setText(Objects.requireNonNull(car).toString());
                Toast.makeText(mLocalActivity, "价格:" + car.getPrice(), Toast.LENGTH_SHORT).show();
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
                bundle.putFloat("msg", Float.valueOf(text));
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

    private Car getCar()
    {
        switch (mCarGroup.getCheckedRadioButtonId())
        {
            case R.id.car_bmw:
                return Car.newBMW();
            case R.id.car_benz:
                return Car.newBENZ();
            case R.id.car_audi:
                return Car.newAudi();
            default:
                return null;
        }
    }
}
