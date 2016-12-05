package com.kun.news.component;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kun.baselib.component.ActivityComponent;
import com.kun.baselib.component.ComponentActivity;
import com.kun.news.app.NewsApplication;

/**
 * Created by kun on 2016/11/18.
 */

public class CurActivityComponent extends ActivityComponent {

    @Override
    public void onCreate(@Nullable ComponentActivity activity, @Nullable Bundle savedInstanceState) {
        NewsApplication.getInstance().setCurActivity(activity);
        super.onCreate(activity, savedInstanceState);
    }

    @Override
    public void onResume() {
        NewsApplication.getInstance().setCurActivity(getActivity());
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        clearCurActivity();
    }

    @Override
    public void onDestroy() {
        clearCurActivity();
        super.onDestroy();
    }

    private void clearCurActivity() {
        Activity curActivity = NewsApplication.getInstance().getCurActivity();
        if (curActivity != null && curActivity.equals(getActivity())) {
            NewsApplication.getInstance().setCurActivity(null);
        }
    }
}
