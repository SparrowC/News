package com.kun.news.nba.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kun.news.R;
import com.kun.news.common.adapter.BaseAdapter;
import com.kun.news.nba.model.NewsItem;

/**
 * Created by jiangkun on 16/9/25.
 */

public class NbaFeedAdapter extends BaseAdapter<NewsItem.NewsItemBean> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FeedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nba_feed_viewholder,
                parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FeedViewHolder)holder).bind(mData.get(position));
    }
}
