package com.kun.news.common.activty;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kun.news.R;

/**
 * Created by jiangkun on 16/9/24.
 */

public abstract class SlideActivity extends BaseActivity {
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
