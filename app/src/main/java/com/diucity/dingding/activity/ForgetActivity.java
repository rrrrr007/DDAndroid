package com.diucity.dingding.activity;


import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.binder.ForgetBinder;
import com.diucity.dingding.entity.MessageBack;
import com.diucity.dingding.entity.MessageBean;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.delegate.ForgetDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observer;

public class ForgetActivity extends BaseActivity<ForgetDelegate> {


    @Override
    public DataBinder getDataBinder() {
        return new ForgetBinder();
    }

    @Override
    protected Class getDelegateClass() {
        return ForgetDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        RxView.clicks(viewDelegate.get(R.id.btn_forget_enter)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    String phone = ((EditText) viewDelegate.get(R.id.edt_forget_phone)).getText().toString();
                    if (TextUtils.isEmpty(phone)) {
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar),2,"请输入手机号");
                        return;
                    }
                    String parms = GsonUtils.GsonString(new MessageBean(System.currentTimeMillis(), "5f61fb53-4e8d-4d99-85f8-a1e17059edf8"));
                    binder.work(viewDelegate,parms);
                });
    }
}
