package com.kun.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kun.news.common.activty.BaseActivity;
import com.kun.news.main.MainActivity;

/**
 * Created by jiangkun on 2016/10/30.
 */

public class SplashActivity extends Activity {

    private void redirect() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_splash);
        findViewById(R.id.splash_view).postDelayed(new Runnable() {
            @Override
            public void run() {
                redirect();
            }
        }, 800);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
