package com.diucity.dingding.activity;


import android.widget.Toast;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.AboutDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

public class AboutActivity extends BaseActivity<AboutDelegate> {


    @Override
    protected Class<AboutDelegate> getDelegateClass() {
        return AboutDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void bindEvenListener() {
        //评分
        RxView.clicks(viewDelegate.get(R.id.rl_about_raise)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    Toast.makeText(this, "评分", Toast.LENGTH_SHORT).show();
                });
        //分享
        RxView.clicks(viewDelegate.get(R.id.rl_about_share)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                });
        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_about_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
    }
}
