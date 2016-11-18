package com.kun.news.common.presenter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jiangkun on 16/9/25.
 */

public abstract class BasePresenter<D, V extends IBaseView> {
    protected V mView;
    protected D mData;
    private Object mApi;

    public void bindView(V view) {
        mView = view;
    }

    public void unbindView() {
        mView = null;
    }

    public abstract void refreshData(Object... params);

    public void loadMoreData(Object... params) {
    }

    protected Object getApiService(String url, Class clz) {
        if (mApi == null) {
            synchronized (BasePresenter.class) {
                if (mApi == null) {
                    mApi = getRetrofit(url).create(clz);
                }
            }
        }
        return mApi;
    }

    protected Retrofit getRetrofit(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
