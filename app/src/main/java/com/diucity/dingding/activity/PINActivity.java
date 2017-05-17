package com.diucity.dingding.activity;


import android.view.View;

import com.diucity.dingding.binder.PINBinder;
import com.diucity.dingding.R;
import com.diucity.dingding.delegate.PINDelegate;
import com.diucity.dingding.entity.Send.PaysetBean;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;


public class PINActivity extends BaseActivity<PINDelegate> {
    private String first, second;
    @Override
    public DataBinder getDataBinder() {
        return new PINBinder();
    }

    @Override
    protected Class<PINDelegate> getDelegateClass() {
        return PINDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        //完成PIN设置
        RxView.clicks(viewDelegate.get(R.id.btn_pin_finish)).throttleFirst( 2 , TimeUnit.SECONDS )
                .subscribe(aVoid -> {
                    if (first.equals(second)) {
                        binder.work(viewDelegate,new PaysetBean("0","1",second));
                    }else {
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar),3,"2次交易密码不一致");
                        viewDelegate.setSecondPin();
                    }
                });
        //监听InputView
        RxTextView.afterTextChangeEvents(viewDelegate.get(R.id.input_pin))
                .subscribe(textChangeEvent -> {
                    if (textChangeEvent.editable().toString().length() == 6) {
                        if (viewDelegate.get(R.id.btn_pin_finish).getVisibility() == View.GONE) {
                            first = textChangeEvent.editable().toString();
                            viewDelegate.setFirstPIN();
                        } else {
                            second = textChangeEvent.editable().toString();
                        }
                    }
                });
    }

}