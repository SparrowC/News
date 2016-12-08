package com.kun.baselib.component;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kun.baselib.activty.BaseActivity;

/**
 * Created by kun on 2016/11/18.
 */

public abstract class SlideActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        overridePendingTransition(R.animator.activity_enter, 0);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
//        overridePendingTransition(0, R.animator.activity_exist);
        super.finish();
    }
}
