package com.kun.news.zhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kun.news.R;
import com.kun.news.app.Constant;
import com.kun.news.common.adapter.BaseViewHolder;
import com.kun.news.common.utils.UIUtils;
import com.kun.news.http.bean.zhihu.ZhihuDailyItem;
import com.kun.news.zhihu.ui.NewsDetailActivity;

/**
 * Created by jiangkun on 16/9/25.
 */

public class FeedViewHolder extends BaseViewHolder<ZhihuDailyItem> {
    private CardView mCardView;
    private TextView mTitle;
    private SimpleDraweeView mImage;
    private Context mContext;

    public FeedViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mImage = (SimpleDraweeView) itemView.findViewById(R.id.image);
        mCardView = (CardView) itemView.findViewById(R.id.card_view);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mData != null && !TextUtils.isEmpty(mData.getId())) {
                    Intent intent = new Intent(mContext, NewsDetailActivity.class);
                    intent.putExtra(Constant.EXTRA_NEWS_ID, mData.getId());
                    mContext.startActivity(intent);
                } else {
                    UIUtils.displayToast(mContext, "此条新闻出错了");
                }
            }
        });
    }

    @Override
    public void bind(ZhihuDailyItem data) {
        if (data == null) return;
        mData = data;
        mTitle.setText(data.getTitle());
        if (data.getImages() == null || data.getImages().length == 0) return;
        mImage.setImageURI(data.getImages()[0]);
    }
}