package com.kun.diy_code.code;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.kun.baselib.fragment.AbsFragment;
import com.kun.diy_code.R;
import com.kun.diy_code.news.NewsFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jiangkun on 2017/9/5.
 */

public class DiyCodeFragment extends AbsFragment {

    private ViewPager mViewPager;
    private static final String FRAGMENT_TAG = "android:switcher:" + R.id.view_pager + ":";

    @Override
    protected void initView(View root) {
        mViewPager = (ViewPager) root.findViewById(R.id.view_pager);
    }

    @Override
    protected void initData() {
        List<CodeListFragment> fragments = new ArrayList<>();
        FragmentManager fm = getChildFragmentManager();
        NewsFragment newsFragment = (NewsFragment) fm.findFragmentByTag(FRAGMENT_TAG + 0);
        if (newsFragment == null) {
            newsFragment = new NewsFragment();
        }
        fragments.add(newsFragment);
        mViewPager.setAdapter(new CodePagerAdapter(fm, fragments));
        mViewPager.setCurrentItem(0);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_code;
    }
}
