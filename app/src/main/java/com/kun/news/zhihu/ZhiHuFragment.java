package com.kun.news.zhihu;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.kun.news.R;
import com.kun.news.common.fragment.AbsFragment;
import com.kun.news.http.bean.zhihu.ZhihuDailyItem;
import com.kun.news.zhihu.adapter.FeedAdapter;
import com.kun.news.zhihu.event.ScrollEvent;
import com.kun.news.zhihu.presenter.FeedPresenter;
import com.kun.news.zhihu.presenter.FeedView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * Created by jiangkun on 16/9/24.
 */

public class ZhiHuFragment extends AbsFragment implements OnRefreshListener, OnLoadMoreListener, FeedView {
    private RecyclerView mListView;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private FeedAdapter mAdapter;
    private FeedPresenter mPresenter;
    private String mDate;

    @Override
    protected void initData() {
        mAdapter = new FeedAdapter();
        mListView.setHasFixedSize(true);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        mListView.setAdapter(mAdapter);
        mPresenter = new FeedPresenter();
        mPresenter.bindView(this);
        autoRefresh();
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

    @Override
    protected void initView(View root) {
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
    protected int getLayout() {
        return R.layout.fragment_zhihu;
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshData();
    }

    @Override
    public void onLoadMore() {
        if (TextUtils.isEmpty(mDate)) {
            mSwipeToLoadLayout.setLoadingMore(false);
        } else {
            mPresenter.loadMoreData(mDate);
        }
    }

    public void autoRefresh() {
        mPresenter.refreshData();
    }


    @Override
    public void onRefreshSuccess(List<ZhihuDailyItem> data, Object... date) {
        mDate = (String) date[0];
        mAdapter.setData(data);
        mSwipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onRefreshFailed() {

    }

    @Override
    public void onLoadMoreSuccess(List<ZhihuDailyItem> data, Object... date) {
        mDate = (String) date[0];
        mAdapter.setDataAfterLoadMore(data);
        mSwipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onLoadMoreFailed() {
        mSwipeToLoadLayout.setLoadingMore(false);
    }
}
