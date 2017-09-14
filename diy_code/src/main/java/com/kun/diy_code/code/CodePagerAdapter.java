package com.kun.diy_code.code;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by jiangkun on 2017/9/14.
 */

public class CodePagerAdapter extends FragmentPagerAdapter {
    private List<CodeListFragment> mFragments;

    public CodePagerAdapter(FragmentManager fm, List<CodeListFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }
}
