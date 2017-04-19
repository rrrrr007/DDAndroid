package com.diucity.dingding.delegate;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
        return true;
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    public void clearEdt(){
        ((EditText)get(R.id.edt_forget_phone)).setText("");
    }

    public void lineChange(boolean hasFocus){
        View view = get(R.id.view_forget);
        view.setBackgroundColor(hasFocus? Color.parseColor("#009479"):Color.parseColor("#C0CCC8"));
        RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.height=(hasFocus?2:1);
        view.setLayoutParams(params);
    }

    public void textChange(boolean has){
        get(R.id.iv_forget_icon).setVisibility(has?View.VISIBLE: View.GONE);
    }
}
