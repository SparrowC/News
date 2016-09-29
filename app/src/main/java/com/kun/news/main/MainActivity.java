package com.kun.news.main;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.kun.news.R;
import com.kun.news.common.activty.BaseActivity;
import com.kun.news.common.fragment.AbsFragment;
import com.kun.news.common.utils.UIUtils;
import com.kun.news.common.widget.NoScrollViewPager;
import com.kun.news.nba.ui.NbaFragment;
import com.kun.news.zhihu.event.ScrollEvent;
import com.kun.news.zhihu.ui.ZhihuFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    @Bind(R.id.view_pager)
    NoScrollViewPager mViewPager;

    @Bind(R.id.net_easy_tab)
    ImageView mNetEasyTab;

    @Bind(R.id.zhihu_tab)
    ImageView mZhiHuTab;

    @Bind(R.id.meizi_tab)
    ImageView mMeiZiTab;

    @Bind(R.id.tab_layout)
    ViewGroup mTabLayout;

    @Bind(R.id.main_fab)
    FloatingActionButton mMainFab;

    @Override
    protected void initData() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mMeiZiTab.setOnClickListener(this);
        mZhiHuTab.setOnClickListener(this);
        mNetEasyTab.setOnClickListener(this);
        mMainFab.setOnClickListener(this);
        initTab();
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

    private void initTab() {
        List<AbsFragment> fragments = new ArrayList<>();
        fragments.add(new ZhihuFragment());
        fragments.add(new NbaFragment());
        fragments.add(new NbaFragment());
        mViewPager.setAdapter(new VPFragmentAdapter(getSupportFragmentManager(), fragments));
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);
        onPageSelected(0);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.meizi_tab:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.net_easy_tab:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.zhihu_tab:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.main_fab:
                UIUtils.displayToast(this, "fab");
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTabsLayoutShow(true);
        switch (position) {
            case 0:
                mZhiHuTab.setAlpha(1.0f);
                mNetEasyTab.setAlpha(0.5f);
                mMeiZiTab.setAlpha(0.5f);
                break;
            case 1:
                mZhiHuTab.setAlpha(0.5f);
                mNetEasyTab.setAlpha(1.0f);
                mMeiZiTab.setAlpha(0.5f);
                break;
            case 2:
                mZhiHuTab.setAlpha(0.5f);
                mNetEasyTab.setAlpha(0.5f);
                mMeiZiTab.setAlpha(1.0f);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private boolean mIsTabLayoutShow = true;

    public void setTabsLayoutShow(boolean show) {
        if (show) {
            if (!mIsTabLayoutShow) {//show
                mMainFab.setVisibility(View.VISIBLE);
                mMainFab.startAnimation(getAlphaAnimation(0, 1));
                mTabLayout.startAnimation(getYTranslateAnimation(1, 0));
                mIsTabLayoutShow = true;
            }
        } else {
            if (mIsTabLayoutShow) {//hide
                mMainFab.startAnimation(getAlphaAnimation(1, 0));
                mMainFab.setVisibility(View.GONE);
                mTabLayout.startAnimation(getYTranslateAnimation(0, 1));
                mIsTabLayoutShow = false;
            }
        }
    }

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
