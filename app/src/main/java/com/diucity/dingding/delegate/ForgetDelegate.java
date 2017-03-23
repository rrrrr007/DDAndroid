package com.diucity.dingding.delegate;

import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class ForgetDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ((TextView) get(R.id.toolbar)).setText("验证手机号");
    }
}
