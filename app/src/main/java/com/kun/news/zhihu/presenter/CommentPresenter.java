package com.kun.news.zhihu.presenter;

import com.kun.news.app.Constant;
import com.kun.news.common.presenter.BaseListPresenter;
import com.kun.news.http.api.ZhihuApi;
import com.kun.news.zhihu.model.ZhihuComments;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jiangkun on 16/9/26.
 */

public class CommentPresenter extends BaseListPresenter<ZhihuComments.CommentsBean> {
    private ZhihuApi mZhihuApi;

    @Override
    public void loadMoreData(Object... params) {
        if (params.length <= 0) return;
        if (mZhihuApi == null) {
            synchronized (CommentPresenter.this) {
                mZhihuApi = (ZhihuApi) getApiService(Constant.ZHIHU_URL, ZhihuApi.class);
            }
        }
        Call<ZhihuComments> call = mZhihuApi.getShortComments((String) params[0]);
        call.enqueue(new Callback<ZhihuComments>() {
            @Override
            public void onResponse(Call<ZhihuComments> call, Response<ZhihuComments> response) {
                if (response.isSuccessful()) {
                    ZhihuComments comments = response.body();
                    if (comments != null) {
                        mListData.addAll(comments.getComments());
                        if (mView != null) {
                            mView.onLoadMoreSuccess(mListData);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ZhihuComments> call, Throwable t) {
                if (mView != null) {
                    mView.onLoadMoreFailed();
                }
            }
        });
    }

    @Override
    public void refreshData(Object... params) {
        if (params.length <= 0) return;
        if (mZhihuApi == null) {
            synchronized (CommentPresenter.this) {
                mZhihuApi = (ZhihuApi) getApiService(Constant.ZHIHU_URL, ZhihuApi.class);
            }
        }
        Call<ZhihuComments> call = mZhihuApi.getLongComments((String) params[0]);
        call.enqueue(new Callback<ZhihuComments>() {
            @Override
            public void onResponse(Call<ZhihuComments> call, Response<ZhihuComments> response) {
                if (response.isSuccessful()) {
                    ZhihuComments comments = response.body();
                    if (comments != null) {
                        mListData = comments.getComments();
                        if (mView != null) {
                            mView.onRefreshSuccess(mListData);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ZhihuComments> call, Throwable t) {
                if (mView != null) {
                    mView.onRefreshFailed();
                }
            }
        });
    }
}
