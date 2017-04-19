package com.diucity.dingding.activity;


import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.diucity.dingding.R;
import com.diucity.dingding.app.App;
import com.diucity.dingding.binder.LoginBinder;
import com.diucity.dingding.delegate.LoginDelegate;
import com.diucity.dingding.entity.Send.LoginBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.VersonUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextSwitcher;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;


public class LoginActivity extends BaseActivity<LoginDelegate> implements View.OnFocusChangeListener {
    private boolean phoneEnable;
    private boolean codeEnable;

    @Override
    protected Class getDelegateClass() {
        return LoginDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new LoginBinder();
    }

    @Override
    protected void bindEvenListener() {
        //忘记密码
        RxView.clicks(viewDelegate.get(R.id.tv_login_forget)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.startActivity(ForgetActivity.class);
                });

        //登录
        RxView.clicks(viewDelegate.get(R.id.btn_login_enter)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    EditText phone = viewDelegate.get(R.id.edt_login_phone);
                    EditText code = viewDelegate.get(R.id.edt_login_code);
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 1, "测试");
                    startActivity(new Intent(this, HomeActivity.class));
                    //binder.work(viewDelegate, new LoginBean(phone.getText().toString(), code.getText().toString(), VersonUtils.getVersion(this)));
                });

        //Edt字段监听
        RxTextView.textChanges(viewDelegate.get(R.id.edt_login_phone)).subscribe(charSequence -> {
            phoneEnable = charSequence.length() > 0;
            viewDelegate.textChange(1, phoneEnable);
            viewDelegate.setEnable(phoneEnable && codeEnable, R.id.btn_login_enter);

        });
        RxTextView.textChanges(viewDelegate.get(R.id.edt_login_code)).subscribe(charSequence -> {
            codeEnable = charSequence.length() > 0;
            viewDelegate.textChange(2, codeEnable);
            viewDelegate.setEnable(phoneEnable && codeEnable, R.id.btn_login_enter);
        });

        //清除edt
        RxView.clicks(viewDelegate.get(R.id.iv_login_icon1)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.clearEdt(1);
                });
        RxView.clicks(viewDelegate.get(R.id.iv_login_icon2)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.clearEdt(2);
                });

        //下划线
        viewDelegate.get(R.id.edt_login_phone).setOnFocusChangeListener(this);
        viewDelegate.get(R.id.edt_login_code).setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        viewDelegate.lineChange(v, hasFocus);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //checkNet();
    }

    //首次检查网络
    public void checkNet() {
        ConnectivityManager connectMgr = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
            // unconnect network
            viewDelegate.showSmallWarn();
        }
    }
}
