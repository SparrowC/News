package com.kun.news.nba.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.kun.news.R;
import com.kun.news.app.Constant;
import com.kun.news.common.adapter.BaseViewHolder;
import com.kun.news.http.api.tencent.TencentVideoApi;
import com.kun.news.nba.model.NewsItem;
import com.kun.news.nba.model.PullRealUrlParser;
import com.kun.news.nba.model.VideoRealUrl;
import com.kun.news.nba.ui.NewsDetailActivity;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by jiangkun on 16/9/25.
 */

public class FeedVideoViewHolder extends BaseViewHolder<NewsItem.NewsItemBean> {
    private CardView mCardView;
    private TextView mTitle;
    private TextView mTime;
    private TextView mDigest;
    private JCVideoPlayerStandard mVideo;
    private Context mContext;

    public FeedVideoViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mTime = (TextView) itemView.findViewById(R.id.time);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mDigest = (TextView) itemView.findViewById(R.id.digest);
        mVideo = (JCVideoPlayerStandard) itemView.findViewById(R.id.video);
        mCardView = (CardView) itemView.findViewById(R.id.card_view);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra(Constant.EXTRA_NEWS_ID, mData.index);
                mContext.startActivity(intent);
            }
        });
        mVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void bind(final NewsItem.NewsItemBean data) {
        if (data == null) return;
        mData = data;
        Picasso.with(mContext)
                .load(data.imgurl)
                .into(mVideo.thumbImageView);

        if (TextUtils.isEmpty(data.realUrl)) {
//            mVideo.setUp("", JCVideoPlayer.SCREEN_LAYOUT_LIST, data.title);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.TENCENT_VIDEO_SERVER)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
            TencentVideoApi api = retrofit.create(TencentVideoApi.class);
            Call<String> call = api.getVideoRealUrl(data.vid);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String xmlStr = response.body();
                        PullRealUrlParser parser = new PullRealUrlParser();
                        try {
                            VideoRealUrl realUrl = parser.parse(new ByteArrayInputStream(xmlStr.getBytes("UTF-8")));
                            String vid = TextUtils.isEmpty(realUrl.fn) ? realUrl.vid + ".mp4" : realUrl.fn;
                            String url = realUrl.url + vid + "?vkey=" + realUrl.fvkey;
                            mData.realUrl = url;
                            mVideo.setUp(url, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, data.title);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        } else
            mVideo.setUp(mData.realUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, data.title);

        mTitle.setText(data.title);
        mTime.setText(data.pub_time);
        mDigest.setText(data.abstractX);
    }
}
