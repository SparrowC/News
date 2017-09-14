package com.kun.diy_code.news.presenter;

import com.kun.diy_code.base.mvp.BasePresenter;
import com.kun.diy_code.news.model.ArticleModel;

/**
 * Created by jiangkun on 2017/9/12.
 */

public class NewsPresenter extends BasePresenter<NewsView, ArticleModel> {

    public NewsPresenter() {
        mModel = new ArticleModel();
        mModel.addNotifyListener(this);
    }

    public void requestData() {
        if (mModel != null) {
            mModel.requestData();
        }
    }

    @Override
    public void onFailed(Exception e) {
        if (mView != null) {
            mView.onLoadNewsFailed(e);
        }
    }

    @Override
    public void onSuccess() {
        if (mModel != null && mView != null) {
            mView.onLoadNewsSuccess(mModel.getData());
        }
    }
}
