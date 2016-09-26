package com.kun.news.zhihu.presenter;

import com.kun.news.app.Constant;
import com.kun.news.common.presenter.BasePresenter;
import com.kun.news.http.api.ZhihuApi;
import com.kun.news.http.bean.zhihu.ZhihuStory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jiangkun on 16/9/25.
 */

public class DetailPresenter extends BasePresenter<ZhihuStory, DetailView> {
    private ZhihuApi mZhihuApi;

    @Override
    public void refreshData(Object... params) {
        if (params.length <= 0) {
            mView.onLoadFailed();
            return;
        }
        mZhihuApi = getRetrofit(Constant.ZHIHU_URL).create(ZhihuApi.class);
        Call<ZhihuStory> call = mZhihuApi.getZhihuStory((String) params[0]);
        call.enqueue(new Callback<ZhihuStory>() {
            @Override
            public void onResponse(Call<ZhihuStory> call, Response<ZhihuStory> response) {
                if (response.isSuccessful()) {
                    mData = response.body();
                    if (mData != null
                            && mView != null) {
                        mView.onLoadSuccess(mData);
                    }
                }
            }

            @Override
            public void onFailure(Call<ZhihuStory> call, Throwable t) {
                if (mView != null)
                    mView.onLoadFailed();
            }
        });
    }
}
