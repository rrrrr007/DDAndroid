package com.diucity.dingding.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.diucity.dingding.entity.Back.XXBack;
import com.diucity.dingding.entity.Send.LoginBean;
import com.diucity.dingding.entity.Send.XXBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;
import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.binder.LoginBinder;
import com.diucity.dingding.delegate.LoginDelegate;
import com.diucity.dingding.utils.VersonUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import rx.Observer;


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

    @Override
    protected void bindEvenListener() {
        //忘记密码
        RxView.clicks(viewDelegate.get(R.id.tv_login_forget)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.startActivity(HomeActivity.class);
                });

        //登录
        RxView.clicks(viewDelegate.get(R.id.btn_login_enter)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    Log.d("ch",getPhoneText());
                    //binder.work(viewDelegate, new LoginBean(phone.getText().toString(), code.getText().toString(), VersonUtils.getVersion(this)));
                });

        //Edt字段监听
        RxTextView.textChanges(viewDelegate.get(R.id.edt_login_phone)).subscribe(charSequence -> {
            phoneEnable = charSequence.length() > 0;
            viewDelegate.setEnable(phoneEnable && codeEnable, R.id.btn_login_enter);
            viewDelegate.setVisiable(phoneEnable && phone.hasFocus(),R.id.iv_login_icon1);
            setPhoneText();

        });
        RxTextView.textChanges(viewDelegate.get(R.id.edt_login_code)).subscribe(charSequence -> {
            codeEnable = charSequence.length() > 0;
            viewDelegate.setEnable(phoneEnable && codeEnable, R.id.btn_login_enter);
            viewDelegate.setVisiable(codeEnable && code.hasFocus(),R.id.iv_login_icon2);
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
        viewDelegate.lineChange(v, hasFocus);
        switch (v.getId()){
            case R.id.edt_login_phone:
                viewDelegate.textChange(v,hasFocus&&phoneEnable);
                break;
            case R.id.edt_login_code:
                viewDelegate.textChange(v,hasFocus&&codeEnable);
                break;
        }

    }

    @Override
    public void initData() {
        phone = viewDelegate.get(R.id.edt_login_phone);
        code = viewDelegate.get(R.id.edt_login_code);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkNet();
    }

    //首次检查网络
    public void checkNet() {
        ConnectivityManager connectMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
            // unconnect network
            viewDelegate.showSmallWarn();
        }
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
