package com.kun.news.zhihu.ui;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.kun.news.R;
import com.kun.news.app.Constant;
import com.kun.news.common.fragment.AbsFragment;
import com.kun.news.common.utils.UIUtils;
import com.kun.news.main.VPFragmentAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jiangkun on 16/9/28.
 */

public class ZhihuFragment extends AbsFragment implements ViewPager.OnPageChangeListener {

    TextView mHotTab;
    TextView mNewsTab;
    ViewPager mViewPager;
    View mRedLine1, mRedLine2;

    @Override
    protected void initData() {
        List<AbsFragment> fragments = new ArrayList<>();
        fragments.add(ZhiHuNewsListFragment.newInstance(Constant.HOT));
        fragments.add(ZhiHuNewsListFragment.newInstance(Constant.NEWS));
        mViewPager.setAdapter(new VPFragmentAdapter(getChildFragmentManager(), fragments));
        mViewPager.addOnPageChangeListener(this);
        onPageSelected(0);
    }

    @Override
    protected void initView(View root) {
        mHotTab = (TextView) root.findViewById(R.id.hot_tab);
        mNewsTab = (TextView) root.findViewById(R.id.news_tab);
        mViewPager = (ViewPager) root.findViewById(R.id.view_pager);
        mRedLine1 = root.findViewById(R.id.red_line_1);
        mRedLine2 = root.findViewById(R.id.red_line_2);
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
}
