package com.android.liuwei.myandroidcode.feature.process.car;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.android.liuwei.myandroidcode.feature.process.car.Car.AUDI;
import com.android.liuwei.myandroidcode.feature.process.car.Car.BENZ;
import com.android.liuwei.myandroidcode.feature.process.car.Car.BMW;
import com.android.liuwei.myandroidcode.feature.process.car.service.AAACarDealerService;
import com.android.liuwei.myandroidcode.feature.process.car.service.BBBCarDealerService;
import com.android.liuwei.myandroidcode.feature.process.car.service.CCCCarDealerService;
import com.android.liuwei.myandroidcode.feature.process.car.service.MyServiceConnection.AAACarDealerServiceConnection;
import com.android.liuwei.myandroidcode.feature.process.car.service.MyServiceConnection.BBBCarDealerServiceConnection;
import com.android.liuwei.myandroidcode.feature.process.car.service.MyServiceConnection.CCCCarDealerServiceConnection;

import java.util.Objects;

import butterknife.BindView;

public class CarActivity extends BaseActivity
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
        return R.layout.activity_car;
    }

    private AAACarDealerServiceConnection mAAAServiceConnection = new AAACarDealerServiceConnection();
    private BBBCarDealerServiceConnection mBBBServiceConnection = new BBBCarDealerServiceConnection();
    private CCCCarDealerServiceConnection mCCCServiceConnection = new CCCCarDealerServiceConnection();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initComponent();
        bindService(new Intent(this, AAACarDealerService.class), mAAAServiceConnection, Service.BIND_AUTO_CREATE);
        bindService(new Intent(this, BBBCarDealerService.class), mBBBServiceConnection, Service.BIND_AUTO_CREATE);
        bindService(new Intent(this, CCCCarDealerService.class), mCCCServiceConnection, Service.BIND_AUTO_CREATE);
    }

    private void initComponent()
    {
        findViewById(R.id.get_aaa).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    float price = mAAAServiceConnection.getPrice(getCar(), getDiscount());
                    Toast.makeText(CarActivity.this, String.format("价格：%s", price), Toast.LENGTH_SHORT).show();
                    mReceiveText.setText(String.format("%s 价格：%s", mAAAServiceConnection.getName(), price));
                }
                catch (RemoteException e)
                {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.get_bbb).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    float price = mBBBServiceConnection.getPrice(getCar(), getDiscount());
                    Toast.makeText(CarActivity.this, String.format("价格：%s", price), Toast.LENGTH_SHORT).show();
                    mReceiveText.setText(String.format("%s 价格：%s", mBBBServiceConnection.getName(), price));
                }
                catch (RemoteException e)
                {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.get_ccc).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String text = mInputEdit.getText().toString();

                if (text.isEmpty())
                {
                    text = "1.0";
                }

                if (mCCCServiceConnection.mMessenger != null)
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
                        mCCCServiceConnection.mMessenger.send(msg);
                    }
                    catch (RemoteException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        findViewById(R.id.get_act).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CarActivity.this, RemoteCarActivity.class);
                intent.putExtra("extra_data", getCar());
                startActivityForResult(intent, 1000);
            }
        });
    }

    private ReceiveHandler mReceiveHandler = new ReceiveHandler(this);

    private static class ReceiveHandler extends Handler
    {
        CarActivity mActivity;

        ReceiveHandler(CarActivity activity)
        {
            mActivity = activity;
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
            if (mActivity != null && car != null)
            {
                mActivity.mReceiveText.setText(Objects.requireNonNull(car).toString());
                Toast.makeText(mActivity, "价格:" + car.getPrice(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Messenger mReplyMessenger = new Messenger(mReceiveHandler);

    private float getDiscount()
    {
        String value = mInputEdit.getText().toString();

        if (value.isEmpty())
        {
            return 100F;
        }

        try
        {
            return Float.valueOf(value) / 100F;
        }
        catch (Exception e)
        {
            return 100F;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.log(this, String.format("onActivityResult(%s,%s)", requestCode, resultCode));
        if (data != null)
        {
            Toast.makeText(this, data.getStringExtra("resultData"), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy()
    {
        unbindService(mAAAServiceConnection);
        unbindService(mBBBServiceConnection);
        unbindService(mCCCServiceConnection);
        super.onDestroy();
    }

    private Car mBMWCar;
    private Car mBenzCar;
    private Car mAudiCar;

    private Car getCar()
    {
        switch (mCarGroup.getCheckedRadioButtonId())
        {
            case R.id.car_bmw:
                if (mBMWCar == null)
                {
                    mBMWCar = BMW.instance();
                }
                return mBMWCar;
            case R.id.car_benz:
                if (mBenzCar == null)
                {
                    mBenzCar = BENZ.instance();
                }
                return mBenzCar;
            case R.id.car_audi:
                if (mAudiCar == null)
                {
                    mAudiCar = AUDI.instance();
                }
                return mAudiCar;
            default:
                return null;
        }
    }
}
