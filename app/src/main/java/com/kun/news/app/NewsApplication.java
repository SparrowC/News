package com.kun.news.app;

import android.app.Activity;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by jiangkun on 16/9/25.
 */

public class NewsApplication extends Application {
    private static NewsApplication mInstance;
    private Activity mActivity;

    public static NewsApplication getInstance() {
        if (mInstance == null) {
            synchronized (NewsApplication.class) {
                if (mInstance == null) {
                    mInstance = new NewsApplication();
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

    public void setCurActivity(Activity activity) {
        mActivity = activity;
    }

    public Activity getCurActivity() {
        return mActivity;
    }
}
