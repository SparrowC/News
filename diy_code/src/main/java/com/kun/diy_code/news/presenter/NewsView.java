package com.kun.diy_code.news.presenter;

import com.kun.diy_code.base.mvp.BaseView;
import com.kun.diy_code.model.Article;

import java.util.List;

/**
 * Created by jiangkun on 2017/9/12.
 */

public interface NewsView extends BaseView {
    void onLoadNewsSuccess(List<Article> articles);

    void onLoadNewsFailed(Exception e);
}
