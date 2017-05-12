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
        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_about_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
    }
}
