package com.kun.diy_code.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kun.diy_code.R;
import com.kun.diy_code.model.Article;

/**
 * Created by jiangkun on 2017/9/13.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {
    private SimpleDraweeView mUserImage;
    private TextView mUserName;
    private TextView mCreateTime;
    private TextView mTitle;
    private TextView mNodeNmae;
    private TextView mComment;
    private final Context mContext;
    private Article mData;

    public NewsViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mUserImage = (SimpleDraweeView) itemView.findViewById(R.id.img_user);
        mUserName = (TextView) itemView.findViewById(R.id.txt_user_name);
        mCreateTime = (TextView) itemView.findViewById(R.id.txt_create_time);
        mTitle = (TextView) itemView.findViewById(R.id.txt_title);
        mNodeNmae = (TextView) itemView.findViewById(R.id.txt_node_name);
        mComment = (TextView) itemView.findViewById(R.id.txt_comment);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext == null || mData == null) return;
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri content_url = Uri.parse(mData.getAddress());
                intent.setData(content_url);
                mContext.startActivity(intent);
            }
        });
    }

    public void bindView(Article article) {
        mData = article;
        mUserImage.setImageURI(article.getUser().getAvatarUrl());
        mUserName.setText(article.getUser().getName());
        mCreateTime.setText(article.getCreated_at());
        mTitle.setText(article.getTitle());
        mNodeNmae.setText("#" + article.getNode_name());
        mComment.setText("评论(" + article.getReplies_count() + ")");
    }
}
