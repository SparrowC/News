package com.kun.diy_code.news.model;

import com.kun.diy_code.base.mvp.BaseModel;
import com.kun.diy_code.base.net.Api;
import com.kun.diy_code.model.Article;
import com.kun.diy_code.news.api.NewsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jiangkun on 2017/9/12.
 */

public class ArticleModel extends BaseModel<List<Article>> {

    private NewsApi mNewsApi;

    public ArticleModel() {
        mNewsApi = (NewsApi) getApiService(Api.CODE_API, NewsApi.class);
    }

    @Override
    public void requestData() {
        mNewsApi.getNewsArticles()
                .enqueue(new Callback<List<Article>>() {
                    @Override
                    public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                        if (response.isSuccessful()) {
                            mData = response.body();
                            notifySuccess();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Article>> call, Throwable t) {
                        notifyFailed(new Exception(t.getMessage()));
                    }
                });
    }

    public List<Article> getData() {
        return mData;
    }
}
