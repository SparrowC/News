
package com.kun.news.http.api;


import com.kun.news.http.bean.meizi.GankData;
import com.kun.news.http.bean.meizi.MeiziData;
import com.kun.news.http.bean.meizi.VedioData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

// @formatter:off

/**
 * Created by drakeet on 8/9/15.
 */
public interface GankApi {

    @GET("/api/data/福利/10/{page}")
    Observable<MeiziData> getMeizhiData(@Path("page") int page);

    @GET("/day/{year}/{month}/{day}")
    Observable<GankData> getGankData(@Path("year") int year, @Path("month") int month,
                                     @Path("day") int day);

    @GET("/api/data/休息视频/10/{page}")
    Observable<VedioData> getVedioData(@Path("page") int page);

}
