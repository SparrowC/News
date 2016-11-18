package com.kun.news.common.widget;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kun.news.R;

/**
 * Created by jiangkun on 2016/10/13.
 */

public class NavigationButton extends FrameLayout {
    private ImageView mNavImage;
    private TextView mNavText;
    private Class<?> mClz;
    private Fragment mFragment;
    private Bundle mBundle;
    private String mTag;

    public NavigationButton(Context context) {
        super(context);
        init();
    }

    public NavigationButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NavigationButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NavigationButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.view_navigation_btn, this, true);
        mNavImage = (ImageView) findViewById(R.id.nav_image);
        mNavText = (TextView) findViewById(R.id.nav_tv_title);
    }

    public void init(@DrawableRes int resId, @StringRes int strId, Class<?> clz) {
        mNavImage.setImageResource(resId);
        mNavText.setText(strId);
        mClz = clz;
    }

    public void init(@DrawableRes int resId, String str, Class<?> clz) {
        mNavImage.setImageResource(resId);
        mNavText.setText(str);
        mClz = clz;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }

    public Class<?> getClz() {
        return mClz;
    }

    public void setClz(Class<?> clz) {
        mClz = clz;
    }

    public void setImage(@DrawableRes int resId) {
        mNavImage.setImageResource(resId);
    }

    public void setText(@StringRes int strId) {
        mNavText.setText(strId);
    }

    public void setText(String str) {
        mNavText.setText(str);
    }

    public Bundle getBundle() {
        return mBundle;
    }

    public void setBundle(Bundle bundle) {
        mBundle = bundle;
    }

    @Override
    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            setAlpha(0.5f);
        } else {
            setAlpha(1);
        }
    }
}
