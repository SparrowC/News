package com.kun.news.common.activity;

import android.util.SparseArray;

import com.kun.baselib.component.ComponentActivity;
import com.kun.baselib.component.ComponentType;
import com.kun.baselib.component.IActivityComponent;
import com.kun.news.component.CurActivityComponent;

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
