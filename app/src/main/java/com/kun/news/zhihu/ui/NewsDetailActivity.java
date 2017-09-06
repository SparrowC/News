package com.kun.news.zhihu.ui;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kun.news.R;
import com.kun.news.app.Constant;
import com.kun.news.common.activity.NewsAbsActivity;
import com.kun.news.common.utils.UIUtils;
import com.kun.news.common.utils.WebUtil;
import com.kun.news.zhihu.model.ZhihuStory;
import com.kun.news.zhihu.model.ZhihuStoryExtra;
import com.kun.news.zhihu.presenter.DetailPresenter;
import com.kun.news.zhihu.presenter.DetailView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by jiangkun on 16/9/25.
 */

public class NewsDetailActivity extends NewsAbsActivity implements DetailView {

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
    @Bind(R.id.nest)
    View mNestView;
    @Bind(R.id.comment_layout)
    ViewGroup mCommentLayout;
    @Bind(R.id.photo_view)
    PhotoView mPhotoView;
    private DetailPresenter mPresenter;
    private String mId;
    private ZhihuStory mZhihuStory;
    PhotoViewAttacher mAttacher;

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
        mAttacher = new PhotoViewAttacher(mPhotoView);
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

    @OnClick({R.id.back_btn, R.id.comment_layout, R.id.bg_image, R.id.photo_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                onBackPressed();
                break;
            case R.id.comment_layout:
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra(Constant.EXTRA_NEWS_ID, mId);
                startActivity(intent);
                break;
            case R.id.bg_image:
                if (TextUtils.isEmpty(mZhihuStory.getImage())) return;
                mPhotoView.setImageDrawable(mBgImage.getDrawable());
                mPhotoView.setVisibility(View.VISIBLE);
                break;
            case R.id.photo_view:
                mPhotoView.setVisibility(View.GONE);
                break;
        }
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
        mZhihuStory = story;
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

    @Override
    public void onBackPressed() {
        if (mPhotoView.getVisibility() == View.VISIBLE) {
            mPhotoView.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}
