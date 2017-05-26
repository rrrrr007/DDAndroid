package com.diucity.dingding.activity;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.WebDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

public class WebActivity extends BaseActivity<WebDelegate> {

    @Override
    protected Class<WebDelegate> getDelegateClass() {
        return WebDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void bindEvenListener() {
        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_web_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
    }
}
