package com.kun.news.app;

import android.app.Application;

import com.kun.baselib.ApplicationLike;

import java.util.List;

/**
 * Created by jiangkun on 2017/9/11.
 */

public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        List<ApplicationLike> applicationLikes = getApplicationLikes();
        for (ApplicationLike like : applicationLikes) {
            like.onCreate();
        }
    }

    protected abstract List<ApplicationLike> getApplicationLikes();
}
