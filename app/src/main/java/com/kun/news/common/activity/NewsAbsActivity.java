package com.kun.news.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * All activity should extends {@link NewsAbsActivity}
 * Created by kun on 2016/11/18.
 */

public abstract class NewsAbsActivity extends BaseComponentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayout();
}
