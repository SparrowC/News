package com.kun.news.nba.ui;


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
import com.kun.news.http.bean.zhihu.ZhihuStory;
import com.kun.news.http.bean.zhihu.ZhihuStoryExtra;
import com.kun.news.nba.model.NewsDetail;
import com.kun.news.nba.presenter.NbaDetailPresenter;
import com.kun.news.nba.presenter.NbaDetailView;
import com.kun.news.zhihu.presenter.DetailPresenter;
import com.kun.news.zhihu.presenter.DetailView;
import com.kun.news.zhihu.ui.CommentActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jiangkun on 16/9/25.
 */

public class NewsDetailActivity extends SlideActivity implements NbaDetailView {

    @Bind(R.id.nba_detail)
    TextView mNbaContent;
    @Bind(R.id.bg_image)
    SimpleDraweeView mBgImage;
    @Bind(R.id.title)
    TextView mTitle;

    private NbaDetailPresenter mPresenter;
    private String mId;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.EXTRA_NEWS_ID);
        if (TextUtils.isEmpty(mId)) {
            return;
        }
        mPresenter = new NbaDetailPresenter();
        mPresenter.bindView(this);
        mPresenter.refreshData(mId);

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

    @Override
    protected int getLayout() {
        return R.layout.activity_nba_detail;
    }


    @Override
    public void onLoadSuccess(NewsDetail detail) {
        mBgImage.setImageURI(detail.imgurl);
        mTitle.setText(detail.title);
        String text = "";
        for (int i = 0; i < detail.content.size(); i++) {
            String temp = detail.content.get(i).get("text");
            text += temp == null ? "  " : temp;
        }
        mNbaContent.setText(text);
    }

    @Override
    public void onLoadFailed() {

    }

    @OnClick(R.id.back_btn)
    public void onBack(View view) {
        onBackPressed();
    }

}
