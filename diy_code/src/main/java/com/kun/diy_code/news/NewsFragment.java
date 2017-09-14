package com.kun.diy_code.news;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kun.diy_code.R;
import com.kun.diy_code.code.CodeListFragment;
import com.kun.diy_code.model.Article;
import com.kun.diy_code.news.adapter.NewsAdapter;
import com.kun.diy_code.news.presenter.NewsPresenter;
import com.kun.diy_code.news.presenter.NewsView;

import java.util.List;

/**
 * Created by jiangkun on 2017/9/12.
 */

public class NewsFragment extends CodeListFragment implements NewsView {
    private RecyclerView mRecyclerView;
    private NewsPresenter mNewsPresenter;
    private NewsAdapter mAdapter;

    @Override
    protected void initData() {
        mNewsPresenter = new NewsPresenter();
        mNewsPresenter.bindView(this);
        mNewsPresenter.requestData();
    }

    @Override
    protected void initView(View root) {
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        mAdapter = new NewsAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_code_news;
    }

    @Override
    public void onDestroy() {
        mNewsPresenter.unBindView(this);
        super.onDestroy();
    }

    @Override
    public void onLoadNewsSuccess(List<Article> articles) {
        mAdapter.setData(articles);
    }

    @Override
    public void onLoadNewsFailed(Exception e) {

    }
}
