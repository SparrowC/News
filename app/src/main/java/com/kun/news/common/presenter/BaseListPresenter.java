package com.kun.news.common.presenter;

import java.util.List;


/**
 * Created by jiangkun on 16/9/25.
 */

public abstract class BaseListPresenter<D> extends BasePresenter<D,IBaseListView>{
    protected List<D> mListData;

    public abstract void loadMoreData(Object... params);
}
