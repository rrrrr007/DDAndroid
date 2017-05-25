package com.diucity.dingding.activity;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.EditText;

import com.diucity.dingding.R;
import com.diucity.dingding.binder.ForgetBinder;
import com.diucity.dingding.delegate.ForgetDelegate;
import com.diucity.dingding.entity.Send.SmsBean;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;


public class ForgetActivity extends BaseActivity<ForgetDelegate> {
    private boolean enable;
    private EditText phone;
    private int index = 0;


    @Override
    public DataBinder getDataBinder() {
        return new ForgetBinder();
    }

    @Override
    protected Class getDelegateClass() {
        return ForgetDelegate.class;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void bindEvenListener() {

        //发送短信
        RxView.clicks(viewDelegate.get(R.id.btn_forget_enter)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    binder.work(viewDelegate,new SmsBean(getPhoneText()));
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
            setPhoneText();
        });

        //下划线
        viewDelegate.get(R.id.edt_forget_phone).setOnFocusChangeListener((v, hasFocus) -> {
            viewDelegate.setEnable(!hasFocus,R.id.view_forget);
        });

        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_forget_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.finish();
                });
    }

    @Override
    public void initData() {
        phone = viewDelegate.get(R.id.edt_forget_phone);
    }

    private String getPhoneText(){
        return phone.getText().toString().replaceAll(" ","");
    }

    private void setPhoneText(){
        if (phone.getText().length()<index){
            index = phone.getText().length();
            return;
        }
        index = phone.getText().length();
        if (phone.getText().length()==3||phone.getText().length()==8){
            phone.setText(phone.getText()+" ");
            phone.setSelection(phone.getText().length());
        }
    }
}
