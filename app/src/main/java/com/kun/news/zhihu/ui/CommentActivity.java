package com.kun.news.zhihu.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.kun.news.R;
import com.kun.news.app.Constant;
import com.kun.news.common.activty.SlideActivity;
import com.kun.news.common.presenter.IBaseListView;
import com.kun.news.zhihu.adapter.CommentAdapter;
import com.kun.news.zhihu.presenter.CommentPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jiangkun on 16/9/26.
 */

public class CommentActivity extends SlideActivity implements OnLoadMoreListener, IBaseListView {
    @Bind(R.id.swipe_target)
    RecyclerView mListView;

    @Bind(R.id.swipe_to_load_layout)
    SwipeToLoadLayout mSwipeToLoadLayout;

    @Bind(R.id.title)
    TextView mTitle;

    private CommentAdapter mAdapter;
    private CommentPresenter mPresenter;
    private String mNewsId;

    @Override
    protected void initData() {
        mTitle.setText("评论");
        mSwipeToLoadLayout.setOnLoadMoreListener(this);

        mAdapter = new CommentAdapter();
        mListView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setAdapter(mAdapter);
        mPresenter = new CommentPresenter();
        mPresenter.bindView(this);
        mPresenter.refreshData(mNewsId);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mNewsId = getIntent().getStringExtra(Constant.EXTRA_NEWS_ID);
        if (TextUtils.isEmpty(mNewsId))
            return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbindView();
        ButterKnife.unbind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_comment;
    }


    @OnClick(R.id.back_btn)
    public void onBack(View view){
        onBackPressed();
    }
    @Override
    public void onLoadMore() {
        mPresenter.loadMoreData(mNewsId);
    }


    @Override
    public void onRefreshSuccess(List data, Object... extra) {
        mAdapter.setData(data);
        mSwipeToLoadLayout.setRefreshing(false);
        mSwipeToLoadLayout.setRefreshEnabled(false);
        mSwipeToLoadLayout.setLoadMoreEnabled(false);
    }

    @Override
    public void onRefreshFailed() {

    }

    @Override
    public void onLoadMoreSuccess(List data, Object... extra) {
        mAdapter.setDataAfterLoadMore(data);
        mSwipeToLoadLayout.setLoadingMore(false);
        mSwipeToLoadLayout.setLoadMoreEnabled(false);

    }

    @Override
    public void onLoadMoreFailed() {

    }
}
