package com.diucity.dingding.delegate;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;

/**
 * Created by Administrator on 2017/4/20 0020.
 */
public class ResetDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_reset;
    }

    @Override
    public boolean needShow() {
        return true;
    }

    public void lineChange(View v, boolean hasFocus) {
        View view = null;
        switch (v.getId()) {
            case R.id.edt_reset_old:
                view = get(R.id.view_reset_1);
                break;
            case R.id.edt_reset_new:
                view = get(R.id.view_reset_2);
                break;
            case R.id.edt_reset_affirm:
                view = get(R.id.view_reset_3);
                break;
        }
        view.setBackgroundColor(hasFocus ? Color.parseColor("#009479") : Color.parseColor("#C0CCC8"));
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.height = (hasFocus ? 2 : 1);
        view.setLayoutParams(params);
    }

    public void textChange() {
        EditText old = get(R.id.edt_reset_old);
        EditText mnew = get(R.id.edt_reset_new);
        EditText affirm = get(R.id.edt_reset_affirm);
        setVisiable(!TextUtils.isEmpty(old.getText().toString()) && old.hasFocus(), R.id.iv_reset_icon1);
        setVisiable(!TextUtils.isEmpty(mnew.getText().toString()) && mnew.hasFocus(), R.id.iv_reset_icon2);
        setVisiable(!TextUtils.isEmpty(affirm.getText().toString()) && affirm.hasFocus(), R.id.iv_reset_icon3);
    }

}
