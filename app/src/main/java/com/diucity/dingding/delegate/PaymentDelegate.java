package com.diucity.dingding.delegate;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public class PaymentDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        setEnable(false,R.id.iv_payment_wx);
    }
}
