package com.kun.news.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kun.news.R;

/**
 * Created by kun on 2016/11/18.
 */

public abstract class SlideActivity extends NewsAbsActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.animator.activity_enter, 0);
    }

    @Override
    public void finish() {
        overridePendingTransition(0, R.animator.activity_exist);
        super.finish();
    }
}
