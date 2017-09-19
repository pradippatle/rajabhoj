package com.pradipatle.rajabhoj.rajabhoj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

/**
 * Created by Aeon-02 on 9/1/2017.
 */

public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                        Intent i = new Intent(SplashScreenActivity.this, WebviewActivity.class);
                        startActivity(i);
                        finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
