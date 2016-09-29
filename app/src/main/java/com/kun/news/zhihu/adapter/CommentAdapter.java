package com.kun.news.zhihu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kun.news.R;
import com.kun.news.common.adapter.BaseAdapter;
import com.kun.news.zhihu.model.ZhihuComments;

/**
 * Created by jiangkun on 16/9/26.
 */

public class CommentAdapter extends BaseAdapter<ZhihuComments.CommentsBean> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_viewholder,
                parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CommentViewHolder)holder).bind(mData.get(position));
    }
}
