package com.kun.news.zhihu.ui;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.kun.baselib.fragment.AbsFragment;
import com.kun.news.R;
import com.kun.news.app.Constant;
import com.kun.news.main.VPFragmentAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jiangkun on 16/9/28.
 */

public class ZhihuFragment extends AbsFragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private static final String FRAGMENT_TAG = "android:switcher:" + R.id.view_pager + ":";
    TextView mHotTab;
    TextView mNewsTab;
    ViewPager mViewPager;
    View mRedLine1, mRedLine2;

    @Override
    protected void initView(View root) {
        mHotTab = (TextView) root.findViewById(R.id.hot_tab);
        mNewsTab = (TextView) root.findViewById(R.id.news_tab);
        mViewPager = (ViewPager) root.findViewById(R.id.view_pager);
        mRedLine1 = root.findViewById(R.id.red_line_1);
        mRedLine2 = root.findViewById(R.id.red_line_2);
    }

    @Override
    protected void initData() {
        //to get fragment from fragment manager
        List<AbsFragment> fragments = new ArrayList<>();
        ZhiHuNewsListFragment hotFragment = (ZhiHuNewsListFragment) getChildFragmentManager().findFragmentByTag(FRAGMENT_TAG + 0);
        if (hotFragment == null) {
            hotFragment = ZhiHuNewsListFragment.newInstance(Constant.HOT);
        }
        fragments.add(hotFragment);
        ZhiHuNewsListFragment newsFragment = (ZhiHuNewsListFragment) getChildFragmentManager().findFragmentByTag(FRAGMENT_TAG + 1);
        if (newsFragment == null) {
            newsFragment = ZhiHuNewsListFragment.newInstance(Constant.NEWS);
        }
        fragments.add(newsFragment);

        mViewPager.setAdapter(new VPFragmentAdapter(getChildFragmentManager(), fragments));
        mViewPager.addOnPageChangeListener(this);
        onPageSelected(0);

        mHotTab.setOnClickListener(this);
        mNewsTab.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_zhihu;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            mRedLine1.setVisibility(View.VISIBLE);
            mRedLine2.setVisibility(View.INVISIBLE);
            mHotTab.setTextColor(getActivity().getResources().getColor(R.color.material_red));
            mNewsTab.setTextColor(getActivity().getResources().getColor(R.color.text_black));
        } else {
            mRedLine1.setVisibility(View.INVISIBLE);
            mRedLine2.setVisibility(View.VISIBLE);
            mNewsTab.setTextColor(getActivity().getResources().getColor(R.color.material_red));
            mHotTab.setTextColor(getActivity().getResources().getColor(R.color.text_black));
        }
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hot_tab:
                onPageSelected(0);
                break;
            case R.id.news_tab:
                onPageSelected(1);
                break;
        }
    }
}
