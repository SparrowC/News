package com.kun.news.nba.presenter;

import android.text.TextUtils;

import com.kun.news.app.Constant;
import com.kun.news.common.presenter.BaseListPresenter;
import com.kun.news.common.utils.ACache;
import com.kun.news.common.utils.AppUtils;
import com.kun.news.http.api.tencent.TencentApi;
import com.kun.news.nba.model.JsonParser;
import com.kun.news.nba.model.NewsItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by jiangkun on 16/9/29.
 */

public class NbaFeedPresenter extends BaseListPresenter<NewsItem.NewsItemBean> {
    private TencentApi mTencentApi;

    public NbaFeedPresenter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.TENCENT_SERVER)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        mTencentApi = retrofit.create(TencentApi.class);
    }

    /**
     * @param params 1 articleIds    2 isRefresh
     */
    @Override
    public void refreshData(Object... params) {
        if (params.length < 2) return;
        Call<String> call = mTencentApi.getNewsItem("news", (String) params[0]);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && !TextUtils.isEmpty(response.body())) {
                    String jsonStr = response.body();
                    NewsItem newsItem = JsonParser.parseNewsItem(jsonStr);
                    mListData = newsItem.data;
                    if (mView != null) {
                        mView.onRefreshSuccess(mListData);
                    }
//                    cache.put(key, newsItem);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (mView != null)
                    mView.onRefreshFailed();
            }
        });
    }

    @Override
    public void loadMoreData(Object... params) {
        if (params.length < 2) return;
        Call<String> call = mTencentApi.getNewsItem("news", (String) params[0]);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && !TextUtils.isEmpty(response.body())) {
                    String jsonStr = response.body();
                    NewsItem newsItem = JsonParser.parseNewsItem(jsonStr);
                    mListData.addAll(newsItem.data);
                    if (mView != null) {
                        mView.onLoadMoreSuccess(mListData);
                    }
//                    cache.put(key, newsItem);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (mView != null)
                    mView.onLoadMoreFailed();
            }
        });
    }
}
