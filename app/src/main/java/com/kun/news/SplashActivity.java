package com.kun.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

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
        redirect();
    }
}
