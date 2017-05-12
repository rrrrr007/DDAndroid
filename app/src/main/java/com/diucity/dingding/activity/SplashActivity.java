package com.diucity.dingding.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diucity.dingding.R;
import com.diucity.dingding.utils.ActivityUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActivityUtils.show(this);
        skip();
    }

    public void skip(){
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            finish();
        }, 1000 * 3);
    }

    @Override
    public void onBackPressed() {
    }
}
