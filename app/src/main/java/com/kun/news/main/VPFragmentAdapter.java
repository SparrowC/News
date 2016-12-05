package com.kun.news.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kun.baselib.fragment.AbsFragment;

import java.util.List;

/**
 * Created by jiangkun on 16/9/24.
 */

public class VPFragmentAdapter extends FragmentPagerAdapter {
    List<AbsFragment> mFragmentList;

    public VPFragmentAdapter(FragmentManager fm, List<AbsFragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
