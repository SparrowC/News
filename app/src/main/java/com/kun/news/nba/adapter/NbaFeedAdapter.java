package com.kun.news.nba.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kun.news.R;
import com.kun.news.common.adapter.BaseAdapter;
import com.kun.news.nba.model.NewsItem;


/**
 * Created by jiangkun on 16/9/25.
 */

public class NbaFeedAdapter extends BaseAdapter<NewsItem.NewsItemBean> {
    private static final int FEED_TYPE_VIDEO = 2;
    private static final int FEED_TYPE_NEWS = 0;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FEED_TYPE_VIDEO) {
            return new FeedVideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nba_video_viewholder,
                    parent, false));
        } else
            return new FeedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nba_feed_viewholder,
                    parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == FEED_TYPE_VIDEO) {
            ((FeedVideoViewHolder) holder).bind(mData.get(position));
        } else
            ((FeedViewHolder) holder).bind(mData.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.equals(mData.get(position).atype, "2"))
            return FEED_TYPE_VIDEO;
        else
            return FEED_TYPE_NEWS;
    }
}
