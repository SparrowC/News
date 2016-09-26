package com.kun.news.common.activty;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.Visibility;
import android.view.Gravity;

/**
 * Created by jiangkun on 16/9/24.
 */

public abstract class SlideActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowAnimation();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimation() {
        Transition transition=buildEnterTransition();
        getWindow().setEnterTransition(transition);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Visibility buildEnterTransition() {
        Slide enterTransition=new Slide();
        enterTransition.setDuration(500);
        enterTransition.setSlideEdge(Gravity.RIGHT);
        return enterTransition;
    }

    @Override
    public void onBackPressed() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }else {
            super.onBackPressed();
        }
    }
}
