package com.kun.diy_code.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kun.diy_code.R;
import com.kun.diy_code.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangkun on 2017/9/13.
 */

public class NewsAdapter extends RecyclerView.Adapter {
    private List<Article> mData;

    public NewsAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_code_news, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((NewsViewHolder) holder).bindView(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<Article> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
