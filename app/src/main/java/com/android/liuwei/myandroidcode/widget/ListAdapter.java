package com.android.liuwei.myandroidcode.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.widget.ListAdapter.Holder;

import java.util.List;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-04-17
 * Time: 10:14
 */
public class ListAdapter extends Adapter<Holder>
{
    public interface Callback
    {
        void onItemClick(String name);
    }

    private LayoutInflater mLayoutInflater;
    private List<String> mData;
    private Callback mCallback;

    public ListAdapter(Context context, List<String> list, Callback callback)
    {
        mLayoutInflater = LayoutInflater.from(context);
        mData = list;
        mCallback = callback;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new Holder(mLayoutInflater.inflate(R.layout.comp_nav_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position)
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

    public class Holder extends ViewHolder implements OnClickListener
    {
        Holder(View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        public void setData(String name)
        {
            ((TextView) itemView).setText(name);

            itemView.setTag(name);
        }

        @Override
        public void onClick(View v)
        {
            if (mCallback != null)
            {
                mCallback.onItemClick((String) v.getTag());
            }
        }
    }
}