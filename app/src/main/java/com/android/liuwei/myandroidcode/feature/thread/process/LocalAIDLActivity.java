package com.android.liuwei.myandroidcode.feature.thread.process;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
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

import butterknife.BindView;

public class LocalAIDLActivity extends BaseActivity
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
        findViewById(R.id.post).setOnClickListener(mOnClickListener);
        bindService(new Intent(this, RemoteAIDLService.class), mServiceConnection, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy()
    {
        unbindService(mServiceConnection);
        super.onDestroy();
    }

    private CarManager mCarManager;

    private ServiceConnection mServiceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            LogUtil.info(this, String.format("onServiceConnected(%s)", name.getShortClassName()));
            mCarManager = CarManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            LogUtil.warn(this, String.format("onServiceDisconnected(%s)", name.getShortClassName()));
        }
    };

    private OnClickListener mOnClickListener = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            float discount = Float.valueOf(mInputEdit.getText().toString()) / 10f;

            try
            {
                float price = mCarManager.getPrice(getCar(), discount);

                Toast.makeText(LocalAIDLActivity.this, "价格:" + price, Toast.LENGTH_SHORT).show();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
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
