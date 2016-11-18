package com.kun.news.base.ui;

import android.util.SparseArray;

import com.kun.news.base.ui.component.CurActivityComponent;

/**
 * Created by kun on 2016/11/18.
 */

public abstract class BaseComponentActivity extends ComponentActivity {

    @Override
    protected SparseArray<IActivityComponent> registerComponents() {
        SparseArray<IActivityComponent> components = new SparseArray<>();
        components.append(ComponentType.CUR_ACTIVITY, new CurActivityComponent());
        return components;
    }
}
