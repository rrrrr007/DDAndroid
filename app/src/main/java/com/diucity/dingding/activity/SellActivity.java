package com.diucity.dingding.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.SellDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

public class SellActivity extends BaseActivity<SellDelegate> {

    @Override
    protected Class<SellDelegate> getDelegateClass() {
        return SellDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void bindEvenListener() {
        //返回
        RxView.clicks(viewDelegate.get(R.id.tv_sell_back)).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.finish();
                });
    }
}
