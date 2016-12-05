package com.kun.baselib.component;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by kun on 2016/11/18.
 */

public class ActivityComponent implements IActivityComponent {
    protected ComponentActivity mActivity;

    public Context getContext() {
        return mActivity;
    }

    public ComponentActivity getActivity() {
        return mActivity;
    }

    @Override
    public void onCreate(@Nullable ComponentActivity activity, @Nullable Bundle savedInstanceState) {
        mActivity = activity;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
