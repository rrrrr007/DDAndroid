package com.diucity.dingding.activity;


import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.delegate.Forget2Delegate;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class Forget2Activity extends BaseActivity<Forget2Delegate> {


    @Override
    protected Class<Forget2Delegate> getDelegateClass() {
        return Forget2Delegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        //完成输入
        RxView.clicks(viewDelegate.get(R.id.btn_forget2_finish)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    String code = ((EditText) viewDelegate.get(R.id.edt_forget2_code)).getText().toString();
                    String password = ((EditText) viewDelegate.get(R.id.edt_forget2_password)).getText().toString();
                    if (TextUtils.isEmpty(code) || TextUtils.isEmpty(password)) {
                        //viewDelegate.showNormalWarn();
                        return;
                    }
                    viewDelegate.showLoadingWarn("密码重设中");
                    finish();

                });
        //timer
        TextView timer = viewDelegate.get(R.id.tv_forget2_timer);
        Subscription subscription = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .limit(60)
                .subscribe(num -> {
                    Log.d("ch", num.toString());
                    if (num == 59)
                        timer.setText("重新发送");
                    else
                        timer.setText((59L - num) + "s");
                });
        subscriptions.add(subscription);
        //重新发送
        RxView.clicks(timer).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    if (!timer.getText().toString().equals("重新发送"))
                        return;
                });
    }
}
