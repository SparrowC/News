package com.kun.news.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.kun.news.R;
import com.kun.news.common.activty.BaseActivity;
import com.kun.news.common.widget.NavigationButton;
import com.kun.news.zhihu.event.ScrollEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements  View.OnClickListener {

    NavigationFragment mNavTab;

    @Bind(R.id.main_fab)
    FloatingActionButton mMainFab;

    @Override
    protected void initData() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        FragmentManager manager=getSupportFragmentManager();
        mNavTab= (NavigationFragment) manager.findFragmentById(R.id.nav_tab);
        mNavTab.setUp(this, R.id.container, manager, new NavigationFragment.OnNavTabReselectListener() {
            @Override
            public void onReselect(NavigationButton navigationButton) {

            }
        });
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mMainFab.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_fab:
                Intent intent =new Intent(this,AboutMeActivity.class);
                startActivity(intent);
                break;
        }
    }

    private boolean mIsTabLayoutShow = true;

    public void setTabsLayoutShow(boolean show) {
        if (show) {
            if (!mIsTabLayoutShow) {//show
                mMainFab.setVisibility(View.VISIBLE);
                mMainFab.startAnimation(getAlphaAnimation(0, 1));
                mIsTabLayoutShow = true;
            }
        } else {
            if (mIsTabLayoutShow) {//hide
                mMainFab.startAnimation(getAlphaAnimation(1, 0));
                mMainFab.setVisibility(View.GONE);
                mIsTabLayoutShow = false;
            }
        }
    }

    //todo do hide tab
    @Subscribe
    public void onEvent(ScrollEvent event) {
        switch (event.getType()) {
            case ScrollEvent.SCROLL_UP:
                setTabsLayoutShow(false);
                break;
            case ScrollEvent.SCROLL_DOWN:
                setTabsLayoutShow(true);
                break;
        }
    }

    private AlphaAnimation getAlphaAnimation(float from, float to) {
        AlphaAnimation animation = new AlphaAnimation(from, to);
        animation.setDuration(300);
        animation.setFillAfter(false);
        return animation;
    }

    private TranslateAnimation getYTranslateAnimation(float fromY, float toY) {
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, fromY, Animation.RELATIVE_TO_SELF, toY);
        animation.setDuration(300);
        animation.setFillAfter(true);
        return animation;
    }
}
