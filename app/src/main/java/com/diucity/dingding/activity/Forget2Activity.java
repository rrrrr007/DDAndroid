package com.diucity.dingding.activity;

import android.widget.EditText;

import com.diucity.dingding.delegate.Forget2Delegate;
import com.diucity.dingding.R;
import com.diucity.dingding.binder.Forget2Binder;
import com.diucity.dingding.entity.Send.ResetBean;
import com.diucity.dingding.entity.Send.SmsBean;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

public class Forget2Activity extends BaseActivity<Forget2Delegate> {
    private String phone;
    private boolean codeEnable;
    private boolean passwordEnable;

    @Override
    protected Class<Forget2Delegate> getDelegateClass() {
        return Forget2Delegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new Forget2Binder();
    }

    @Override
    protected void bindEvenListener() {
        //完成输入
        RxView.clicks(viewDelegate.get(R.id.btn_forget2_finish)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    String code = ((EditText) viewDelegate.get(R.id.edt_forget2_code)).getText().toString();
                    String password = ((EditText) viewDelegate.get(R.id.edt_forget2_password)).getText().toString();
                    binder.work(viewDelegate, new ResetBean(phone,code,password));

                });

        //edt字段监听
        RxTextView.textChanges(viewDelegate.get(R.id.edt_forget2_code)).subscribe(charSequence -> {
            codeEnable = charSequence.length()>0;
            viewDelegate.setEnable(codeEnable&&passwordEnable,R.id.btn_forget2_finish);
        });
        RxTextView.textChanges(viewDelegate.get(R.id.edt_forget2_password)).subscribe(charSequence -> {
            passwordEnable = charSequence.length()>0;
            viewDelegate.textChange(charSequence.length()>0);
            viewDelegate.setEnable(codeEnable&&passwordEnable,R.id.btn_forget2_finish);
        });

        //重新发送
        RxView.clicks(viewDelegate.get(R.id.tv_forget2_timer)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    if (viewDelegate.timer())
                        binder.work(viewDelegate,new SmsBean(phone));
                });

        //清除Edt
        RxView.clicks(viewDelegate.get(R.id.iv_forget2_icon)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.clearEdt();
                });

        //下划线
        viewDelegate.get(R.id.edt_forget2_code).setOnFocusChangeListener((v, hasFocus) -> {
            viewDelegate.setEnable(!hasFocus,R.id.view_forget2_1);
        });
        viewDelegate.get(R.id.edt_forget2_password).setOnFocusChangeListener((v, hasFocus) -> {
            viewDelegate.setEnable(!hasFocus,R.id.view_forget2_2);
        });

        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_forget2_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.startActivity(ForgetActivity.class);
                    viewDelegate.finish();
                });
    }

    @Override
    public void initData() {
        phone = getIntent().getStringExtra("phone");
        viewDelegate.timer();
    }

}
