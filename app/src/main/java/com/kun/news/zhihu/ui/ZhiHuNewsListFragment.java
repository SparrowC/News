package com.kun.news.zhihu.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.kun.news.R;
import com.kun.news.app.Constant;
import com.kun.news.common.adapter.BaseAdapter;
import com.kun.news.common.fragment.AbsFragment;
import com.kun.news.common.presenter.BasePresenter;
import com.kun.news.zhihu.adapter.FeedAdapter;
import com.kun.news.zhihu.adapter.HotFeedAdapter;
import com.kun.news.zhihu.event.ScrollEvent;
import com.kun.news.zhihu.presenter.ZhihuFeedPresenter;
import com.kun.news.zhihu.presenter.FeedView;
import com.kun.news.zhihu.presenter.ZhihuHotFeedPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;


/**
 * Created by jiangkun on 16/9/24.
 */

public class ZhiHuNewsListFragment extends AbsFragment implements OnRefreshListener, OnLoadMoreListener, FeedView {

    private RecyclerView mListView;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private BaseAdapter mAdapter;
    private BasePresenter mPresenter;
    private String mDate;
    private int mType;
    @Bind(R.id.progress)
    ProgressBar mProgressBar;

    public static ZhiHuNewsListFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.EXTRA_ZHIHU_TYPE, type);
        ZhiHuNewsListFragment fragment = new ZhiHuNewsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initData() {
        switch (mType) {
            case Constant.HOT:
                mPresenter = new ZhihuHotFeedPresenter();
                mAdapter = new HotFeedAdapter();
                break;
            case Constant.NEWS:
                mPresenter = new ZhihuFeedPresenter();
                mAdapter = new FeedAdapter();
                break;
        }
        mPresenter.bindView(this);
        mListView.setHasFixedSize(true);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        mListView.setAdapter(mAdapter);

        mProgressBar.setVisibility(View.VISIBLE);
        onRefresh();
        mListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) < 10 || Math.abs(dx) > Math.abs(dy)) return;
                if (dy < 0) {//down->show
                    EventBus.getDefault().post(new ScrollEvent(ScrollEvent.SCROLL_DOWN));
                } else {//up->hide
                    EventBus.getDefault().post(new ScrollEvent(ScrollEvent.SCROLL_UP));
                }
            }
        });
    }

    private void initArguments() {
        mType = getArguments().getInt(Constant.EXTRA_ZHIHU_TYPE);
        if (mType != Constant.HOT && mType != Constant.NEWS)
            return;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_zhihu_news_list;
    }

    @Override
    protected void initView(View root) {
        initArguments();
        mListView = (RecyclerView) root.findViewById(R.id.swipe_target);
        mSwipeToLoadLayout = (SwipeToLoadLayout) root.findViewById(R.id.swipe_to_load_layout);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbindView();
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshData(mType);
    }

    @Override
    public void onLoadMore() {
        if (TextUtils.isEmpty(mDate)) {
            mSwipeToLoadLayout.setLoadingMore(false);
        } else {
            mPresenter.loadMoreData(mDate);
        }
    }

    @Override
    public void onRefreshSuccess(List data, Object... extra) {
        mProgressBar.setVisibility(View.GONE);
        if (extra.length <= 0) {
            mSwipeToLoadLayout.setLoadMoreEnabled(false);
        } else {
            mDate = (String) extra[0];
        }
        mAdapter.setData(data);
        mSwipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onRefreshFailed() {

    }

    @Override
    public void onLoadMoreSuccess(List data, Object... extra) {
        mDate = (String) extra[0];
        mAdapter.setDataAfterLoadMore(data);
        mSwipeToLoadLayout.setLoadingMore(false);
    }


    @Override
    public void onLoadMoreFailed() {
        mSwipeToLoadLayout.setLoadingMore(false);
    }
}
