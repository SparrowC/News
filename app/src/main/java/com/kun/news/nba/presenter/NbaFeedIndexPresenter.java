package com.kun.news.nba.presenter;

import com.kun.news.app.Constant;
import com.kun.news.common.presenter.BasePresenter;
import com.kun.news.http.api.tencent.TencentApi;
import com.kun.news.nba.model.NewsIndex;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jiangkun on 16/9/29.
 */

public class NbaFeedIndexPresenter extends BasePresenter<NewsIndex, NbaFeedIndexView> {
    TencentApi mTencentApi;

    @Override
    public void refreshData(Object... params) {
        if (params.length < 1) return;
        mTencentApi = (TencentApi) getApiService(Constant.TENCENT_SERVER, TencentApi.class);
        Call<NewsIndex> call = mTencentApi.getNewsIndex((String) params[0]);
        call.enqueue(new Callback<NewsIndex>() {
            @Override
            public void onResponse(Call<NewsIndex> call, Response<NewsIndex> response) {
                if (response.isSuccessful()) {
                    NewsIndex newsIndex = response.body();
                    if (newsIndex != null) {
                        mData = newsIndex;
                        if (mView != null) {
                            mView.onLoadSuccess(mData);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsIndex> call, Throwable t) {

            }
        });
    }
}
