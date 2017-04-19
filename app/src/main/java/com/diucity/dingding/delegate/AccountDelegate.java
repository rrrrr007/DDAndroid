package com.diucity.dingding.delegate;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;

/**
 * Created by Administrator on 2017/4/19 0019.
 */
public class AccountDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    public boolean needShow() {
        return false;
    }
}
