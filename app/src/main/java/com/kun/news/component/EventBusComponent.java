package com.kun.news.component;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kun.baselib.component.ActivityComponent;
import com.kun.baselib.component.ComponentActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by kun on 2016/12/5.
 */

public class EventBusComponent extends ActivityComponent {
    @Override
    public void onCreate(@Nullable ComponentActivity activity, @Nullable Bundle savedInstanceState) {
        super.onCreate(activity, savedInstanceState);
        if (!EventBus.getDefault().isRegistered(mActivity)) {
            EventBus.getDefault().register(mActivity);
        }
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
