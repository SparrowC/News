package com.kun.news.common.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by jiangkun on 16/9/24.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    protected List<T> mData;
    private int mPreItemCounts = 0;

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
        mPreItemCounts = getItemCount();
    }

    public void setDataAfterLoadMore(List<T> data) {
        mData = data;
        notifyItemRangeInserted(mPreItemCounts, getItemCount() - mPreItemCounts);
        mPreItemCounts = getItemCount();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
