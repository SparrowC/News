package com.kun.news.zhihu.presenter;

import android.text.TextUtils;

import com.kun.news.app.Constant;
import com.kun.news.common.presenter.BasePresenter;
import com.kun.news.http.api.ZhihuApi;
import com.kun.news.zhihu.model.ZhihuStory;
import com.kun.news.zhihu.model.ZhihuStoryExtra;

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
        if (mZhihuApi == null) {
            synchronized (DetailPresenter.this) {
                mZhihuApi = (ZhihuApi) getApiService(Constant.ZHIHU_URL, ZhihuApi.class);
            }
        }
//        mZhihuApi = getRetrofit(Constant.ZHIHU_URL).create(ZhihuApi.class);
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

    public void requestExtra(String id) {
        if (TextUtils.isEmpty(id)) return;
        if (mZhihuApi == null) {
            synchronized (DetailPresenter.this) {
                mZhihuApi = (ZhihuApi) getApiService(Constant.ZHIHU_URL, ZhihuApi.class);
            }
        }
        Call<ZhihuStoryExtra> call = mZhihuApi.getZhihuStoryExtra(id);
        call.enqueue(new Callback<ZhihuStoryExtra>() {
            @Override
            public void onResponse(Call<ZhihuStoryExtra> call, Response<ZhihuStoryExtra> response) {
                if (response.isSuccessful()) {
                    ZhihuStoryExtra extra = response.body();
                    if (extra != null && mView != null) {
                        mView.onLoadExtraSuccess(extra);
                    }
                }
            }

            @Override
            public void onFailure(Call<ZhihuStoryExtra> call, Throwable t) {
                if (mView != null) {
                    mView.onLoadExtraFailed();
                }
            }
        });
    }
}
