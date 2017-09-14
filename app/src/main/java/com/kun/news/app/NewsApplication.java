package com.kun.news.app;

import android.app.Activity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.kun.baselib.ApplicationLike;
import com.kun.diy_code.base.app.DiyCodeApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangkun on 16/9/25.
 */

public class NewsApplication extends BaseApplication {
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

    @Override
    protected List<ApplicationLike> getApplicationLikes() {
        List<ApplicationLike> applicationLikes = new ArrayList<>();
        applicationLikes.add(new DiyCodeApplication());
        return applicationLikes;
    }

    public void setCurActivity(Activity activity) {
        mActivity = activity;
    }

    public Activity getCurActivity() {
        return mActivity;
    }
}
