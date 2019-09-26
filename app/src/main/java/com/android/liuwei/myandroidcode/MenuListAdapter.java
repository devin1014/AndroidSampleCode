package com.android.liuwei.myandroidcode;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.liuwei.myandroidcode.MenuList.MenuInfo;
import com.android.liuwei.myandroidcode.MenuListAdapter.Holder;

import java.util.List;

/**
 * User: liuwei
 * Date: 2018-04-17
 * Time: 10:14
 */
public class MenuListAdapter extends Adapter<Holder>
{
    public interface Callback
    {
        void onItemClick(MenuInfo item);
    }

    private LayoutInflater mLayoutInflater;
    private List<MenuInfo> mData;
    private Callback mCallback;
    private int mItemLayoutId;

    public MenuListAdapter(Context context, List<MenuInfo> list, Callback callback, int layoutId)
    {
        mLayoutInflater = LayoutInflater.from(context);
        mData = list;
        mCallback = callback;
        mItemLayoutId = layoutId;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new Holder(mLayoutInflater.inflate(mItemLayoutId, parent, false));
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

    private MenuInfo getItem(int position)
    {
        return mData.get(position);
    }

    class Holder extends ViewHolder implements OnClickListener
    {
        Holder(View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        public void setData(MenuInfo menuInfo)
        {
            ((TextView) itemView).setText(menuInfo.name);

            itemView.setTag(menuInfo);
        }

        @Override
        public void onClick(View v)
        {
            if (mCallback != null)
            {
                mCallback.onItemClick((MenuInfo) v.getTag());
            }
        }
    }
}