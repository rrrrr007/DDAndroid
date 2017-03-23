package com.diucity.dingding.delegate;

import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class UserInfoDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ((TextView) get(R.id.toolbar)).setText("个人信息");
    }
}
