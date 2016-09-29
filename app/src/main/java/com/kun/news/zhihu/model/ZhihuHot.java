package com.kun.news.zhihu.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jiangkun on 16/9/28.
 */

public class ZhihuHot {

    /**
     * news_id : 8809320
     * url : http://news-at.zhihu.com/api/2/news/8809320
     * thumbnail : http://pic1.zhimg.com/0e38a36fddea5cf768751bba5e46dd54.jpg
     * title : 瞎扯 · 如何正确地吐槽
     */

    @SerializedName("recent")
    private List<RecentBean> recent;

    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    public static class RecentBean {
        @SerializedName("news_id")
        private int news_id;
        @SerializedName("url")
        private String url;
        @SerializedName("thumbnail")
        private String thumbnail;
        @SerializedName("title")
        private String title;

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
