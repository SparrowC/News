package com.kun.diy_code.base.net;

/**
 * Created by jiangkun on 2017/9/11.
 */

public class Api {
    public static final String CODE_API_HOST = "https://diycode.cc";
    public static final String CODE_API = CODE_API_HOST + "/api/v3/";
    public static final String NEWS_PATH = "/news.json";
    public static final String TOPICS_PATH = "/topics.json";


    public static String codeApi() {
        return CODE_API_HOST + CODE_API;
    }

    public static String getUrl(String path) {
        return codeApi() + path;
    }
}
