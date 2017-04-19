package com.diucity.dingding.delegate;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;


/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class LoginDelegate extends AppDelegate {

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public boolean needShow() {
        return true;
    }

    @Override
    public void initWidget() {
        super.initWidget();

    }

    public void lineChange(View v,boolean hasFocus){
        View view = null;
        switch (v.getId()){
            case R.id.edt_login_phone:
                view = get(R.id.view_login_1);
                break;
            case R.id.edt_login_code:
                view = get(R.id.view_login_2);
                break;
        }
        view.setBackgroundColor(hasFocus? Color.parseColor("#009479"):Color.parseColor("#C0CCC8"));
        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) view.getLayoutParams();
        params.height=(hasFocus?2:1);
        view.setLayoutParams(params);
    }

    public void textChange(int i,boolean has){
        View view = null;
        switch (i){
            case 1:
                view = get(R.id.iv_login_icon1);
                break;
            case 2:
                view = get(R.id.iv_login_icon2);
                break;
        }
        view.setVisibility(has?View.VISIBLE: View.GONE);
    }

    public void clearEdt(int i){
        EditText edt = null;
        switch (i){
            case 1:
                edt = get(R.id.edt_login_phone);
                break;
            case 2:
                edt = get(R.id.edt_login_code);
                break;
        }
        edt.setText("");
    }

}
