package com.diucity.dingding.activity;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.OptionsDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.NotificationsUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

public class OptionsActivity extends BaseActivity<OptionsDelegate> {


    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected Class<OptionsDelegate> getDelegateClass() {
        return OptionsDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        //通知开关
        RxView.clicks(viewDelegate.get(R.id.ll_options_notification)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,NotificationActivity.class));
                });
        //重设密码
        RxView.clicks(viewDelegate.get(R.id.ll_options_resetPassword)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                });
        //忘记密码
        RxView.clicks(viewDelegate.get(R.id.ll_options_forgetPassword)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,ForgetActivity.class));
                });
        //清空缓存
        RxView.clicks(viewDelegate.get(R.id.ll_options_clearCache)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {

                });
        //版本升级
        RxView.clicks(viewDelegate.get(R.id.ll_options_version)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {

                });
        //关于APP
        RxView.clicks(viewDelegate.get(R.id.ll_options_about)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {

                });
        //功能介绍
        RxView.clicks(viewDelegate.get(R.id.ll_options_function)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {

                });
    }
}
