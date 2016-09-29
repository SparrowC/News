package com.kun.news.http.api;


import com.kun.news.http.bean.zhihu.ZhihuComments;
import com.kun.news.http.bean.zhihu.ZhihuDaily;
import com.kun.news.http.bean.zhihu.ZhihuHot;
import com.kun.news.http.bean.zhihu.ZhihuStory;
import com.kun.news.http.bean.zhihu.ZhihuStoryExtra;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ZhihuApi {

    //get today's news
    @GET("/api/4/news/latest")
    Call<ZhihuDaily> getLastDaily();

    @GET("/api/4/news/hot")
    Call<ZhihuHot> getHot();

    //get news on date
    @GET("/api/4/news/before/{date}")
    Call<ZhihuDaily> getTheDaily(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Call<ZhihuStory> getZhihuStory(@Path("id") String id);

    @GET("/api/4/story-extra/{id}")
    Call<ZhihuStoryExtra> getZhihuStoryExtra(@Path("id") String id);

    @GET("/api/4/story/{id}/long-comments")
    Call<ZhihuComments> getLongComments(@Path("id") String id);

    @GET("/api/4/story/{id}/short-comments")
    Call<ZhihuComments> getShortComments(@Path("id") String id);
}
