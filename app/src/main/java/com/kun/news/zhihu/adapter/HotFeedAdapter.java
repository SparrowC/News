package com.kun.news.zhihu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kun.news.R;
import com.kun.news.common.adapter.BaseAdapter;
import com.kun.news.http.bean.zhihu.ZhihuDailyItem;
import com.kun.news.http.bean.zhihu.ZhihuHot;

/**
 * Created by jiangkun on 16/9/25.
 */

public class HotFeedAdapter extends BaseAdapter<ZhihuHot.RecentBean> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotFeedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_viewholder,
                parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HotFeedViewHolder) holder).bind(mData.get(position));
    }
}
