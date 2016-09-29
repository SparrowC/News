package com.kun.news.nba.model;


import java.io.InputStream;

/**
 * @author yuyh.
 * @date 16/7/1.
 */
public interface RealUrlParser {

    VideoRealUrl parse(InputStream is) throws Exception;
}
