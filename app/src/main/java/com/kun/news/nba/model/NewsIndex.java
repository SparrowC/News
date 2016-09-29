package com.kun.news.nba.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuyh.
 * @date 16/6/3.
 */
public class NewsIndex extends Base {


    /**
     * type : news
     * id : 20160603042788
     * column : banner
     * needUpdate : 0
     */

    @SerializedName("data")
    public List<IndexBean> data;

    public static class IndexBean implements Serializable {
        @SerializedName("type")
        public String type;
        @SerializedName("id")
        public String id;
        @SerializedName("column")
        public String column;
        @SerializedName("needUpdate")
        public String needUpdate;
    }
}
