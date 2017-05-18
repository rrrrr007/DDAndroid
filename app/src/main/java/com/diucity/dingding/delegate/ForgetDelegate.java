package com.diucity.dingding.delegate;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.R;

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
        return true;
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    public void clearEdt(){
        ((EditText)get(R.id.edt_forget_phone)).setText("");
    }

    public void textChange(boolean has){
        get(R.id.iv_forget_icon).setVisibility(has?View.VISIBLE: View.GONE);
    }
}
