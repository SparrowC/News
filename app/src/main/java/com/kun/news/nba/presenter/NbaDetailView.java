package com.kun.news.nba.presenter;

import com.kun.news.common.presenter.IBaseView;
import com.kun.news.nba.model.NewsDetail;

/**
 * Created by jiangkun on 16/9/29.
 */
public interface NbaDetailView extends IBaseView {
    void onLoadSuccess(NewsDetail detail);

    void onLoadFailed();
}
