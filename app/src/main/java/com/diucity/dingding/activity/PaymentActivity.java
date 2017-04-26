package com.diucity.dingding.activity;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.PaymentDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

public class PaymentActivity extends BaseActivity<PaymentDelegate> {
    private final int WXPAY =101;
    private final int YWTPAY =102;
    private final int ZFBPAY =103;
    private int choice =WXPAY;


    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected Class<PaymentDelegate> getDelegateClass() {
        return PaymentDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_payment_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.finish();
                });
        //支付选择
        RxView.clicks(viewDelegate.get(R.id.iv_payment_wx)).subscribe(aVoid -> {
            choice = WXPAY;
            setPayIcon();
        });
        RxView.clicks(viewDelegate.get(R.id.iv_payment_ywt)).subscribe(aVoid -> {
            choice = YWTPAY;
            setPayIcon();
        });
        RxView.clicks(viewDelegate.get(R.id.iv_payment_zfb)).subscribe(aVoid -> {
            choice = ZFBPAY;
            setPayIcon();
        });
    }

    private void setPayIcon(){
        switch (choice){
            case 101:
                viewDelegate.setEnable(false,R.id.iv_payment_wx);
                viewDelegate.setEnable(true,R.id.iv_payment_ywt);
                viewDelegate.setEnable(true,R.id.iv_payment_zfb);
                break;
            case 102:
                viewDelegate.setEnable(false,R.id.iv_payment_ywt);
                viewDelegate.setEnable(true,R.id.iv_payment_wx);
                viewDelegate.setEnable(true,R.id.iv_payment_zfb);
                break;
            case 103:
                viewDelegate.setEnable(false,R.id.iv_payment_zfb);
                viewDelegate.setEnable(true,R.id.iv_payment_wx);
                viewDelegate.setEnable(true,R.id.iv_payment_ywt);
                break;
        }
    }
}
