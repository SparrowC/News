package com.kun.news.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by kun on 2016/11/18.
 */

public interface IActivityComponent {
    void onCreate(@Nullable ComponentActivity activity, @Nullable Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onRestart();

    void onDestroy();

    void onNewIntent(Intent intent);

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
