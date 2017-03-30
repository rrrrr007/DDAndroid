package com.diucity.dingding.activity;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.diucity.dingding.api.Network;
import com.diucity.dingding.R;
import com.diucity.dingding.app.App;
import com.diucity.dingding.binder.LoginBinder;
import com.diucity.dingding.delegate.LoginDelegate;
import com.diucity.dingding.delegate.UserInfoDelegate;
import com.diucity.dingding.entity.MessageBack;
import com.diucity.dingding.entity.MessageBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observer;


public class LoginActivity extends BaseActivity<LoginDelegate> {
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
        super.bindEvenListener();
        //忘记密码
        RxView.clicks(viewDelegate.get(R.id.tv_login_forget)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this, PriceActivity.class));
                });
        //登录
        RxView.clicks(viewDelegate.get(R.id.btn_login_enter)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    EditText code = viewDelegate.get(R.id.edt_login_code);
                    EditText phone = viewDelegate.get(R.id.edt_login_phone);
                    if (TextUtils.isEmpty(code.getText().toString()) || TextUtils.isEmpty(phone.getText().toString())) {
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar),2,"请输入账号或密码");
                        return;
                    }
                    String parms = GsonUtils.GsonString(new MessageBean(System.currentTimeMillis(), "5f61fb53-4e8d-4d99-85f8-a1e17059edf8"));
                    binder.work(viewDelegate,parms);
                });
    }

    public void checkNet(){
         ConnectivityManager connectMgr = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
                    NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                    NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                    if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
                        Log.i("ch", "aaaaaaaaaunconnect");
                        // unconnect network
                        for (BaseActivity baseActivity : App.activities) {
                            baseActivity.isNetWork(true);
                        }
                    }else {
                        Log.i("ch", "aaaaaaaaaaaconnect");
                        // connect network
                    }
    }



}
