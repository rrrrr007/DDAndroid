package com.diucity.dingding.delegate;

import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.R;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class WalletDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    public boolean needShow() {
        return true;
    }


}
