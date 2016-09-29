package com.kun.news.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by jiangkun on 16/9/25.
 */

public class NewsApplication extends Application {
    private static NewsApplication mInstance;

    public static NewsApplication getInstance() {
        if(mInstance==null){
            synchronized (NewsApplication.class){
                if(mInstance==null){
                    mInstance=new NewsApplication();
                }
            }
        }
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
