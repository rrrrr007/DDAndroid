package com.diucity.dingding.delegate;

import android.text.TextUtils;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.SpUtils;

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

    @Override
    public void initWidget() {
        String s = SpUtils.getString(getActivity(), SpUtils.WALLET);
        setText(TextUtils.isEmpty(s) ? "0.00" : s, R.id.tv_wallet_money);
    }
}
