package com.kun.news.zhihu.presenter;

import com.kun.news.app.Constant;
import com.kun.news.common.presenter.BaseListPresenter;
import com.kun.news.http.api.ZhihuApi;
import com.kun.news.zhihu.model.ZhihuHot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jiangkun on 16/9/25.
 */

public class ZhihuHotFeedPresenter extends BaseListPresenter<ZhihuHot.RecentBean> {
    private ZhihuApi mZhihuApi;

    @Override
    public void refreshData(Object... params) {
        if (mZhihuApi == null) {
            synchronized (ZhihuHotFeedPresenter.this) {
                mZhihuApi = (ZhihuApi) getApiService(Constant.ZHIHU_URL, ZhihuApi.class);
            }
        }
        Call<ZhihuHot> call = mZhihuApi.getHot();
        call.enqueue(new Callback<ZhihuHot>() {
            @Override
            public void onResponse(Call<ZhihuHot> call, Response<ZhihuHot> response) {
                if (response.isSuccessful()) {
                    ZhihuHot daily = response.body();
                    if (daily != null) {
                        mListData = daily.getRecent();
                        if (mView != null)
                            mView.onRefreshSuccess(mListData);
                    }
                }
            }

            @Override
            public void onFailure(Call<ZhihuHot> call, Throwable t) {
                if (mView != null)
                    mView.onRefreshFailed();
            }

        });

    }

    @Override
    public void loadMoreData(Object... params) {
        //can not load more
    }

}
