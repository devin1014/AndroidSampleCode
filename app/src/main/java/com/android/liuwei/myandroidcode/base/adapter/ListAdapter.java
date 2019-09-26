package com.android.liuwei.myandroidcode.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.base.adapter.ListAdapter.ListHolder;

import java.util.List;

/**
 * User: liuwei
 * Date: 2018-04-17
 * Time: 10:14
 */
public class ListAdapter extends Adapter<ListHolder>
{
    private LayoutInflater mLayoutInflater;
    private List<String> mData;
    private int mItemLayoutId;

    public ListAdapter(Context context, List<String> list, int layoutId)
    {
        mLayoutInflater = LayoutInflater.from(context);
        mData = list;
        mItemLayoutId = layoutId;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new ListHolder(mLayoutInflater.inflate(mItemLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position)
    {
        holder.setData(getItem(position));
    }

    @Override
    public int getItemCount()
    {
        return mData != null ? mData.size() : 0;
    }

    private String getItem(int position)
    {
        return mData.get(position);
    }

    class ListHolder extends ViewHolder implements OnClickListener
    {
        ListHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public void setData(String data)
        {
            ((TextView) itemView).setText(data);
        }

        @Override
        public void onClick(View v)
        {
        }
    }
}