package com.android.liuwei.myandroidcode.deviceinfo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.BaseActivity;
import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.deviceinfo.DeviceInfoHelper.DeviceInfo;

import butterknife.BindView;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-05-07
 * Time: 14:43
 */
@SuppressLint("Registered")
public class DeviceInfoActivity extends BaseActivity
{
    @BindView(R.id.device_id)
    TextView mDeviceId;
    @BindView(R.id.device_mac)
    TextView mDeviceMac;
    @BindView(R.id.device_info)
    TextView mDeviceInfo;

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_device_info;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initComponent();
    }

    private void initComponent()
    {
        String deviceId = DeviceUtil.getDeviceId(this);
        mDeviceId.setText(deviceId != null ? deviceId : "null");

        String mac = DeviceUtil.getMacString(this);
        mDeviceMac.setText(mac != null ? mac : "null");

        DeviceInfo info = DeviceInfoHelper.getDeviceInfo(this);
        mDeviceInfo.setText(info != null ? info.toString() : "null");
    }
}
