package com.kun.news.nba.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kun.news.R;
import com.kun.news.app.Constant;
import com.kun.news.common.adapter.BaseViewHolder;
import com.kun.news.nba.model.NewsItem;
import com.kun.news.nba.ui.NewsDetailActivity;

/**
 * Created by jiangkun on 16/9/25.
 */

public class FeedViewHolder extends BaseViewHolder<NewsItem.NewsItemBean> {
    private CardView mCardView;
    private TextView mTitle;
    private TextView mTime;
    private TextView mDigest;
    private SimpleDraweeView mImage;
    private Context mContext;

    public FeedViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mTime = (TextView) itemView.findViewById(R.id.time);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mDigest = (TextView) itemView.findViewById(R.id.digest);
        mImage = (SimpleDraweeView) itemView.findViewById(R.id.image);
        mCardView = (CardView) itemView.findViewById(R.id.card_view);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra(Constant.EXTRA_NEWS_ID, mData.index);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void bind(NewsItem.NewsItemBean data) {
        if (data == null) return;
        mData = data;
        mImage.setImageURI(data.imgurl);
        mTitle.setText(data.title);
        mTime.setText(data.pub_time);
        mDigest.setText(data.abstractX);
    }

}
