package com.kun.news.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.kun.baselib.fragment.AbsFragment;
import com.kun.diy_code.code.DiyCodeFragment;
import com.kun.news.R;
import com.kun.news.app.Constant;
import com.kun.news.common.widget.NavigationButton;
import com.kun.news.nba.ui.NbaFragment;
import com.kun.news.zhihu.ui.ZhihuFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jiangkun on 2016/10/13.
 */

public class NavigationFragment extends AbsFragment {
    @Bind(R.id.nav_zixun)
    NavigationButton mNavZixun;

    @Bind(R.id.nav_nba_news)
    NavigationButton mNavNbaNews;

    @Bind(R.id.nav_nba_video)
    NavigationButton mNavNbaVideo;

    @Bind(R.id.nav_diy_code)
    NavigationButton mNavCode;

    private NavigationButton mCurNavBtn;
    private OnNavTabReselectListener mNavTabReselectListener;
    private FragmentManager mFragmentManager;
    private Context mContext;
    private int mContainerId;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View root) {
        mNavZixun.init(R.drawable.tab_icon_explore, R.string.zhihu, ZhihuFragment.class);
        mNavNbaNews.init(R.drawable.tab_icon_new, R.string.nba_news, NbaFragment.class);
        mNavNbaNews.setTag("news");

        mNavNbaVideo.init(R.drawable.tab_icon_tweet, R.string.nba_video, NbaFragment.class);
        mNavNbaVideo.setTag("videos");

        mNavCode.init(R.drawable.tab_icon_me, R.string.code, DiyCodeFragment.class);
    }

    @Override
    protected int getLayout() {
        return R.layout.nav_tab;
    }

    public void setUp(Context context, @IdRes int id, FragmentManager manager, OnNavTabReselectListener listener) {
        mContext = context;
        mContainerId = id;
        mFragmentManager = manager;
        mNavTabReselectListener = listener;
        mCurNavBtn = mNavZixun;
        mNavZixun.setSelected(true);
        doSelect(null, mNavZixun);
    }

    @OnClick({R.id.nav_zixun, R.id.nav_nba_news, R.id.nav_nba_video, R.id.nav_diy_code})
    public void onClick(View view) {
        mNavZixun.setSelected(false);
        mNavNbaNews.setSelected(false);
        mNavNbaVideo.setSelected(false);
        mNavCode.setSelected(false);
        view.setSelected(true);
        if (view instanceof NavigationButton) {
            if (view != mCurNavBtn) {
                doSelect(mCurNavBtn, (NavigationButton) view);
                mCurNavBtn = (NavigationButton) view;
            } else {
                if (mNavTabReselectListener != null) {
                    mNavTabReselectListener.onReselect((NavigationButton) view);
                }
            }
        }
    }

    private void doSelect(NavigationButton oldNavBtn, NavigationButton newNavBtn) {
        if (mFragmentManager == null) {
            throw new IllegalArgumentException("FragmentManager cannot be null in com.kun.news.main.NavigationFragment.setUp()");
        }
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (oldNavBtn != null && oldNavBtn.getFragment() != null) {
            ft.detach(oldNavBtn.getFragment());
        }
        if (newNavBtn != null) {
            if (newNavBtn.getFragment() == null) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.EXTRA_NBA_TYPE, newNavBtn.getTag());

                Fragment fragment = Fragment.instantiate(mContext
                        , newNavBtn.getClz().getName(), bundle);
                ft.add(mContainerId, fragment, newNavBtn.getTag());
                newNavBtn.setFragment(fragment);
            } else {
                ft.attach(newNavBtn.getFragment());
            }
        }
        ft.commit();
    }


    public interface OnNavTabReselectListener {
        void onReselect(NavigationButton navigationButton);
    }
}
