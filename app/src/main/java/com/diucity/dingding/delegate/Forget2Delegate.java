package com.diucity.dingding.delegate;

import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class Forget2Delegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_forget2;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ((TextView) get(R.id.toolbar)).setText("重设密码");
    }
}
