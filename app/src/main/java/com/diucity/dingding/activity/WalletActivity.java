package com.diucity.dingding.activity;

import android.content.Intent;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.WalletDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

public class WalletActivity extends BaseActivity<WalletDelegate> {


    @Override
    protected Class<WalletDelegate> getDelegateClass() {
        return WalletDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        //绑定银行卡
        RxView.clicks(viewDelegate.get(R.id.ll_wallet_bankCard)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,WebActivity.class));
                });
    }
}
