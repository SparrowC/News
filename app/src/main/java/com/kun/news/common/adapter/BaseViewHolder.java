package com.kun.news.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jiangkun on 16/9/25.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    protected T mData;
    public BaseViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bind(T data);
}
