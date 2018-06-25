package com.android.liuwei.myandroidcode.lebocast;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.R;
import com.hpplay.sdk.source.browse.api.LelinkServiceInfo;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-20
 * Time: 14:27
 */
public class DeviceHolder extends ViewHolder
{
    private TextView name;
    private TextView description;
    private TextView status;

    DeviceHolder(View itemView, OnClickListener listener)
    {
        super(itemView);

        itemView.setOnClickListener(listener);
        name = itemView.findViewById(R.id.device_name);
        description = itemView.findViewById(R.id.device_description);
        status = itemView.findViewById(R.id.device_status);
    }

    @SuppressLint("SetTextI18n")
    public void setData(LelinkServiceInfo info)
    {
        itemView.setTag(info);
        name.setText(info.getName());
        description.setText(info.getIp() + " , " + info.getTypes());
        status.setText("connected:" + info.isConnect() + " , online:" + info.isOnLine());
    }
}
