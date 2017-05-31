package com.diucity.dingding.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.diucity.dingding.R;
import com.diucity.dingding.binder.LoginBinder;
import com.diucity.dingding.delegate.LoginDelegate;
import com.diucity.dingding.entity.Send.LoginBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.VersonUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;


public class LoginActivity extends BaseActivity<LoginDelegate> implements View.OnFocusChangeListener {
    private boolean phoneEnable, codeEnable;
    private EditText phone, code;
    private int index = 0;

    @Override
    protected Class getDelegateClass() {
        return LoginDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new LoginBinder();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
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
                    binder.work(viewDelegate, new LoginBean(getPhoneText(), code.getText().toString(), VersonUtils.getVersion(this)));
                });

        //Edt字段监听
        RxTextView.textChanges(viewDelegate.get(R.id.edt_login_phone)).subscribe(charSequence -> {
            phoneEnable = charSequence.length() > 0;
            viewDelegate.setEnable(phoneEnable && codeEnable, R.id.btn_login_enter);
            viewDelegate.setVisiable(phoneEnable && phone.hasFocus(), R.id.iv_login_icon1);
            setPhoneText(charSequence);

        });
        RxTextView.textChanges(viewDelegate.get(R.id.edt_login_code)).subscribe(charSequence -> {
            codeEnable = charSequence.length() > 0;
            viewDelegate.setEnable(phoneEnable && codeEnable, R.id.btn_login_enter);
            viewDelegate.setVisiable(codeEnable && code.hasFocus(), R.id.iv_login_icon2);
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

        viewDelegate.getRootView().addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            viewDelegate.setWidgetHeight();
        });


    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edt_login_phone:
                viewDelegate.setEnable(!hasFocus, R.id.view_login_1);
                viewDelegate.textChange(v, hasFocus && phoneEnable);
                break;
            case R.id.edt_login_code:
                viewDelegate.setEnable(!hasFocus, R.id.view_login_2);
                viewDelegate.textChange(v, hasFocus && codeEnable);
                break;
        }

    }

    @Override
    public void initData() {
        phone = viewDelegate.get(R.id.edt_login_phone);
        code = viewDelegate.get(R.id.edt_login_code);
    }


    private String getPhoneText() {
        return phone.getText().toString().replaceAll(" ", "");
    }

    private void setPhoneText(CharSequence s) {
        Log.d("ch","index"+s.length());
        index = s.toString().length();
        if (s.toString().length() == 3 || s.toString().length() == 8) {
            phone.setText(phone.getText() + " ");
            Log.d("ch","执行光标滞后"+s.toString().length());
            phone.setSelection(phone.getText().length());
        }
    }

    public void showNormal() {
        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 1, "重置成功");
    }


}
