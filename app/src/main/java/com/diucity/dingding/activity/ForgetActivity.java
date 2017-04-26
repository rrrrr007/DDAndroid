package com.diucity.dingding.activity;


import android.widget.EditText;

import com.diucity.dingding.R;
import com.diucity.dingding.binder.ForgetBinder;
import com.diucity.dingding.entity.Send.SmsBean;
import com.diucity.dingding.delegate.ForgetDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;


public class ForgetActivity extends BaseActivity<ForgetDelegate> {
    private boolean enable;


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

        //发送短信
        RxView.clicks(viewDelegate.get(R.id.btn_forget_enter)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    String phone = ((EditText) viewDelegate.get(R.id.edt_forget_phone)).getText().toString();
                    //startActivity(new Intent(this,Forget2Activity.class));
                    binder.work(viewDelegate,new SmsBean(phone));
                });

        //清除Edt
        RxView.clicks(viewDelegate.get(R.id.iv_forget_icon)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.clearEdt();
                });

        //Edt字段监听
        RxTextView.textChanges(viewDelegate.get(R.id.edt_forget_phone)).subscribe(charSequence -> {
            enable = charSequence.length()>0;
            viewDelegate.textChange(enable);
            viewDelegate.setEnable(enable,R.id.btn_forget_enter);
        });

        //下划线
        viewDelegate.get(R.id.edt_forget_phone).setOnFocusChangeListener((v, hasFocus) -> {
            viewDelegate.lineChange(hasFocus);
        });

        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_forget_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.finish();
                });
    }
}
