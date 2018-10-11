package liuwei.android.core.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import liuwei.android.core.widget.ListAdapter.Holder;

/**
 * User: liuwei
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
    private int mItemLayoutId;

    public ListAdapter(Context context, List<String> list, Callback callback, int layoutId)
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