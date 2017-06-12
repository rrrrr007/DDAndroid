package com.diucity.dingding.activity;

import android.view.View;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.WithdrawDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;
import com.tencent.smtt.sdk.WebView;

import java.util.concurrent.TimeUnit;

public class WithdrawActivity extends BaseActivity<WithdrawDelegate> {


    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected Class<WithdrawDelegate> getDelegateClass() {
        return WithdrawDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_withdraw_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
    }





}
