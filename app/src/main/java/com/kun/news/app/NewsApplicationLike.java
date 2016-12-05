package com.kun.news.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.kun.news.app.hotfix.NewsApplicationContext;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by jiangkun on 16/9/25.
 */
@DefaultLifeCycle(application = "com.kun.news.app.hotfix.SimpleApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL)

public class NewsApplicationLike extends DefaultApplicationLike {
    private static NewsApplicationLike mInstance;
    private Activity mActivity;

    public NewsApplicationLike(Application application, int tinkerFlags
            , boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime
            , long applicationStartMillisTime, Intent tinkerResultIntent
            , Resources[] resources, ClassLoader[] classLoader, AssetManager[] assetManager) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent, resources, classLoader, assetManager);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        NewsApplicationContext.mApplication = getApplication();
        NewsApplicationContext.mContext = getApplication();
        mInstance = this;

        MultiDex.install(base);
        TinkerInstaller.install(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        getApplication().registerActivityLifecycleCallbacks(callback);
    }

    public static NewsApplicationLike getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(NewsApplicationContext.mContext);
    }

    public void setCurActivity(Activity activity) {
        mActivity = activity;
    }

    public Activity getCurActivity() {
        return mActivity;
    }
}
