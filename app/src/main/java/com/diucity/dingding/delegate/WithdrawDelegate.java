package com.diucity.dingding.delegate;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;

/**
 * Created by Administrator on 2017/4/20 0020.
 */
public class WithdrawDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    public boolean needShow() {
        return false;
    }
}
