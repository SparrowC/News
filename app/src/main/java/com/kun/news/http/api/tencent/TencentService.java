package com.kun.news.http.api.tencent;

import com.kun.news.app.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author yuyh.
 * @date 16/6/3.
 */
public class TencentService {

    static Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.TENCENT_SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    static TencentApi api = retrofit.create(TencentApi.class);


//    /**
//     * 获取所有新闻索引
//     *
//     * @param isRefresh 是否重新请求数据
//     * @param cbk
//     */
//    public static void getNewsIndex(Constant.NewsType newsType, boolean isRefresh, final RequestCallback<NewsIndex> cbk) {
//        final String key = "getNewsIndex" + newsType.getType();
//        final ACache cache = ACache.get(AppUtils.getAppContext());
//        Object obj = cache.getAsObject(key);
//        if (obj != null && !isRefresh) {
//            NewsIndex index = (NewsIndex) obj;
//            cbk.onSuccess(index);
//            return;
//        }
//
//        Call<String> call = api.getNewsIndex(newsType.getType());
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response != null && !TextUtils.isEmpty(response.body())) {
//                    String jsonStr = response.body();
//                    NewsIndex index = JsonParser.parseWithGson(NewsIndex.class, jsonStr);
//                    cbk.onSuccess(index);
//                    cache.put(key, index);
//                    LogUtils.d("resp:" + jsonStr);
//                } else {
//                    cbk.onFailure("获取数据失败");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                cbk.onFailure(t.getMessage());
//            }
//        });
//    }
//
//    /**
//     * 根据索引获取新闻列表
//     *
//     * @param articleIds 索引值。多个索引以“,”分隔
//     * @param isRefresh  是否重新请求数据
//     * @param cbk
//     */
//    public static void getNewsItem(Constant.NewsType newsType, String articleIds, boolean isRefresh, final RequestCallback<NewsItem> cbk) {
//        final String key = "getNewsItem" + articleIds;
//        final ACache cache = ACache.get(AppUtils.getAppContext());
//        Object obj = cache.getAsObject(key);
//        if (obj != null && !isRefresh) {
//            NewsItem newsItem = (NewsItem) obj;
//            cbk.onSuccess(newsItem);
//            return;
//        }
//
//        Call<String> call = api.getNewsItem(newsType.getType(), articleIds);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response != null && !TextUtils.isEmpty(response.body())) {
//                    String jsonStr = response.body();
//                    NewsItem newsItem = JsonParser.parseNewsItem(jsonStr);
//                    cbk.onSuccess(newsItem);
//                    cache.put(key, newsItem);
//                    LogUtils.d("resp:" + jsonStr);
//                } else {
//                    cbk.onFailure("获取数据失败");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                cbk.onFailure(t.getMessage());
//            }
//        });
//    }
//
//    /**
//     * 获取新闻的详细内容
//     *
//     * @param newsType  文章类型
//     * @param articleId 文章id
//     * @param isRefresh 是否重新请求数据
//     * @param cbk
//     */
//    public static void getNewsDetail(Constant.NewsType newsType, String articleId, boolean isRefresh, final RequestCallback<NewsDetail> cbk) {
//        final String key = "getNewsDetail" + articleId;
//        final ACache cache = ACache.get(AppUtils.getAppContext());
//        Object obj = cache.getAsObject(key);
//        if (obj != null && !isRefresh) {
//            NewsDetail detail = (NewsDetail) obj;
//            cbk.onSuccess(detail);
//            return;
//        }
//
//        Call<String> call = api.getNewsDetail(newsType.getType(), articleId);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response != null && !TextUtils.isEmpty(response.body())) {
//                    String jsonStr = response.body();
//                    NewsDetail detail = JsonParser.parseNewsDetail(jsonStr);
//                    cbk.onSuccess(detail);
//                    cache.put(key, detail);
//                    LogUtils.d("resp:" + jsonStr);
//                } else {
//                    cbk.onFailure("获取数据失败");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                cbk.onFailure(t.getMessage());
//            }
//        });
//    }


}
