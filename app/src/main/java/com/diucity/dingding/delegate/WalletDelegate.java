package com.diucity.dingding.delegate;

import android.text.TextUtils;

import com.diucity.dingding.R;
import com.diucity.dingding.entity.Back.ListBack;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SpUtils;
import com.diucity.dingding.utils.StringUtils;
import com.diucity.dingding.utils.TimeUtils;

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
        String str = SpUtils.getString(getActivity(), SpUtils.RECORD);
        if (!TextUtils.isEmpty(str)) {
            ListBack back = GsonUtils.GsonToBean(str, ListBack.class);
            if (back.getData().getItems().size() > 0) {
                setText((TimeUtils.getTime(back.getData().getItems().get(0).getTime()) + " 收入 ￥" + StringUtils.fmoney(back.getData().getItems().get(0).getAmount(), 2)), R.id.tv_wallet_time);
            }
        }
    }
}
