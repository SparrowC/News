package com.kun.diy_code.base.mvp;

/**
 * Created by jiangkun on 2017/9/12.
 */

public abstract class BasePresenter<V extends BaseView, T extends BaseModel> implements INotifyListener {
    protected T mModel;
    protected V mView;

    public void bindView(V view) {
        mView = view;
    }

    public void unBindView(V view) {
        mView = null;
    }

}
