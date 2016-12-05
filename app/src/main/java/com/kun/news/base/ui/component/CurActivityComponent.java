package com.kun.news.base.ui.component;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kun.news.app.NewsApplicationLike;
import com.kun.news.base.ui.ActivityComponent;
import com.kun.news.base.ui.ComponentActivity;

/**
 * Created by kun on 2016/11/18.
 */

public class CurActivityComponent extends ActivityComponent {

    @Override
    public void onCreate(@Nullable ComponentActivity activity, @Nullable Bundle savedInstanceState) {
        NewsApplicationLike.getInstance().setCurActivity(activity);
        super.onCreate(activity, savedInstanceState);
    }

    @Override
    public void onResume() {
        NewsApplicationLike.getInstance().setCurActivity(getActivity());
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
        Activity curActivity = NewsApplicationLike.getInstance().getCurActivity();
        if (curActivity != null && curActivity.equals(getActivity())) {
            NewsApplicationLike.getInstance().setCurActivity(null);
        }
    }
}
