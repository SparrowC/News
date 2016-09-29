package com.kun.news.zhihu.presenter;

import com.kun.news.common.presenter.IBaseView;
import com.kun.news.http.bean.zhihu.ZhihuStory;
import com.kun.news.http.bean.zhihu.ZhihuStoryExtra;

/**
 * Created by jiangkun on 16/9/25.
 */

public interface DetailView extends IBaseView {
    void onLoadSuccess(ZhihuStory story);

    void onLoadFailed();

    void onLoadExtraSuccess(ZhihuStoryExtra extra);
    void onLoadExtraFailed();
}
