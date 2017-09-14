package com.kun.diy_code.news.api;

import com.kun.diy_code.model.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jiangkun on 2017/9/12.
 */

public interface NewsApi {

    @GET("news.json")
    Call<List<Article>> getNewsArticles();
}
