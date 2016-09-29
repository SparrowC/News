package com.kun.news.zhihu.presenter;

import com.kun.news.app.Constant;
import com.kun.news.common.presenter.BaseListPresenter;
import com.kun.news.http.api.ZhihuApi;
import com.kun.news.zhihu.model.ZhihuDaily;
import com.kun.news.zhihu.model.ZhihuDailyItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jiangkun on 16/9/25.
 */

public class ZhihuFeedPresenter extends BaseListPresenter<ZhihuDailyItem> {
    private ZhihuApi mZhihuApi;

    @Override
    public void refreshData(Object... params) {
        if (mZhihuApi == null) {
            synchronized (ZhihuFeedPresenter.this) {
                mZhihuApi = (ZhihuApi) getApiService(Constant.ZHIHU_URL, ZhihuApi.class);
            }
        }
        Call<ZhihuDaily> call = mZhihuApi.getLastDaily();
        call.enqueue(new Callback<ZhihuDaily>() {
            @Override
            public void onResponse(Call<ZhihuDaily> call, Response<ZhihuDaily> response) {
                if (response.isSuccessful()) {
                    ZhihuDaily daily = response.body();
                    if (daily != null) {
                        String date = daily.getDate();
                        mListData = daily.getStories();
                        if (mView != null)
                            mView.onRefreshSuccess(mListData, date);
                    }
                }
            }

            @Override
            public void onFailure(Call<ZhihuDaily> call, Throwable t) {
                if (mView != null)
                    mView.onRefreshFailed();
            }
        });

    }

    @Override
    public void loadMoreData(Object... params) {
        if (params.length <= 0) return;
        if (mZhihuApi == null) {
            synchronized (ZhihuFeedPresenter.this) {
                mZhihuApi = (ZhihuApi) getApiService(Constant.ZHIHU_URL, ZhihuApi.class);
            }
        }
        Call<ZhihuDaily> call = mZhihuApi.getTheDaily((String) params[0]);
        call.enqueue(new Callback<ZhihuDaily>() {
            @Override
            public void onResponse(Call<ZhihuDaily> call, Response<ZhihuDaily> response) {
                if (response.isSuccessful()) {
                    ZhihuDaily daily = response.body();
                    if (daily != null) {
                        String date = daily.getDate();
                        mListData.addAll(daily.getStories());
                        if (mView != null)
                            mView.onLoadMoreSuccess(mListData, date);
                    }
                }
            }

            @Override
            public void onFailure(Call<ZhihuDaily> call, Throwable t) {
                if (mView != null)
                    mView.onLoadMoreFailed();
            }
        });
    }

}
