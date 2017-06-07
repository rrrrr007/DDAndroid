package com.diucity.dingding.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.diucity.dingding.R;
import com.diucity.dingding.utils.ActivityUtils;
import com.diucity.dingding.utils.SpUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActivityUtils.show(this);
        skip();
    }

    public void skip() {
        new Handler().postDelayed(() -> {
            if (!TextUtils.isEmpty(SpUtils.getString(this, SpUtils.USER))) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }

        }, 1000 * 3);
    }

    @Override
    public void onBackPressed() {
    }
}
