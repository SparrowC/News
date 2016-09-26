package com.kun.news.http.api;

/**
 * Created by jiangkun on 16/9/24.
 */

public class ApiManager {

    private static ApiManager mInstance;
    private ZhihuApi mZhihuApi;

    private ApiManager() {
    }

    public static ApiManager getmInstance() {
        if (mInstance == null) {
            synchronized (ApiManager.class) {
                if (mInstance == null) {
                    mInstance = new ApiManager();
                }
            }
        }
        return mInstance;
    }

    public ZhihuApi getZhihuApi() {
        return mZhihuApi;
    }
}
