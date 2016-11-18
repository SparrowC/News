package com.kun.news.common.presenter;

import java.util.List;

/**
 * Created by jiangkun on 16/9/25.
 */

public interface IBaseListView<D> extends IBaseView {
    void onRefreshSuccess(List<D> data, Object... extra);

    void onRefreshFailed();

    void onLoadMoreSuccess(List<D> data, Object... extra);

    void onLoadMoreFailed();
}
