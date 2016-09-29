package com.kun.news.zhihu.ui;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kun.news.R;
import com.kun.news.app.Constant;
import com.kun.news.common.activty.SlideActivity;
import com.kun.news.common.utils.UIUtils;
import com.kun.news.common.utils.WebUtil;
import com.kun.news.zhihu.model.ZhihuStory;
import com.kun.news.zhihu.model.ZhihuStoryExtra;
import com.kun.news.zhihu.presenter.DetailPresenter;
import com.kun.news.zhihu.presenter.DetailView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jiangkun on 16/9/25.
 */

public class NewsDetailActivity extends SlideActivity implements DetailView {

    @Bind(R.id.wv_zhihu)
    WebView mWebView;
    @Bind(R.id.bg_image)
    SimpleDraweeView mBgImage;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.likes)
    TextView mLikes;
    @Bind(R.id.comment_count)
    TextView mCommentCount;
    private DetailPresenter mPresenter;
    private String mId;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.EXTRA_NEWS_ID);
        if (TextUtils.isEmpty(mId)) {
            return;
        }
        mPresenter = new DetailPresenter();
        mPresenter.bindView(this);
        mPresenter.refreshData(mId);
        mPresenter.requestExtra(mId);

        mLikes.setText(0 + "赞");
        mCommentCount.setText(0 + "评论");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.unbindView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.back_btn)
    public void onBack(View view) {
        onBackPressed();
    }

    @OnClick(R.id.comment_count)
    public void enterComment(View view) {
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra(Constant.EXTRA_NEWS_ID, mId);
        startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void onLoadSuccess(ZhihuStory story) {
        if (story == null) {
            UIUtils.displayToast(this, "加载失败!");
            return;
        }
        mTitle.setText(story.getTitle());
        mBgImage.setImageURI(story.getImage());
        if (TextUtils.isEmpty(story.getBody())) {
            mWebView.loadUrl(story.getShareUrl());
        } else {
            String data = WebUtil.buildHtmlWithCss(story.getBody(), story.getCss(), false);
            mWebView.loadDataWithBaseURL(WebUtil.BASE_URL, data, WebUtil.MIME_TYPE,
                    WebUtil.ENCODING, WebUtil.FAIL_URL);
        }
    }

    @Override
    public void onLoadFailed() {

    }

    @Override
    public void onLoadExtraSuccess(ZhihuStoryExtra extra) {
        mLikes.setText(extra.getPopularity() + "赞");
        mCommentCount.setText(extra.getComments() + "评论");
    }

    @Override
    public void onLoadExtraFailed() {

    }
}
