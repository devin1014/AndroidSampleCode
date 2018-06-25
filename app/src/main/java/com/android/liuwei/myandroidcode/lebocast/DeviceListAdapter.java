package com.android.liuwei.myandroidcode.lebocast;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.android.liuwei.myandroidcode.R;
import com.hpplay.sdk.source.browse.api.LelinkServiceInfo;

import java.util.List;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-20
 * Time: 14:27
 */
public class DeviceListAdapter extends Adapter<DeviceHolder>
{
    private LayoutInflater mLayoutInflater;
    private List<LelinkServiceInfo> mLelinkList;
    private OnClickListener mOnClickListener;

    public DeviceListAdapter(LayoutInflater inflater, OnClickListener listener)
    {
        mLayoutInflater = inflater;
        mOnClickListener = listener;
    }

    public void setLelinkList(List<LelinkServiceInfo> list)
    {
        mLelinkList = list;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DeviceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new DeviceHolder(mLayoutInflater.inflate(R.layout.list_cast_device, parent, false), mOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceHolder holder, int position)
    {
        holder.setData(getItem(position));
    }

    @Override
    public int getItemCount()
    {
        return mLelinkList != null ? mLelinkList.size() : 0;
    }

    public LelinkServiceInfo getItem(int pos)
    {
        return mLelinkList != null ? mLelinkList.get(pos) : null;
    }
}
