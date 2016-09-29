package com.kun.news.nba.presenter;

import com.kun.news.common.presenter.IBaseView;
import com.kun.news.nba.model.NewsIndex;

/**
 * Created by jiangkun on 16/9/29.
 */
public interface NbaFeedIndexView extends IBaseView{
    void onLoadSuccess(NewsIndex newsIndex);
    void onLoadFailed();
}
