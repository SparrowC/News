package com.kun.news.component;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kun.baselib.component.ActivityComponent;
import com.kun.baselib.component.ComponentActivity;

import butterknife.ButterKnife;

/**
 * Created by kun on 2016/12/5.
 */

public class ButterKnifeComponent extends ActivityComponent {

    @Override
    public void onCreate(@Nullable ComponentActivity activity, @Nullable Bundle savedInstanceState) {
        super.onCreate(activity, savedInstanceState);
        ButterKnife.bind(activity);
    }

    @Override
    public void onDestroy() {
        ButterKnife.unbind(mActivity);
        super.onDestroy();
    }
}
