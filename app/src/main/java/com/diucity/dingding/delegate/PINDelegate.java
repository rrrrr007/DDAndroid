package com.diucity.dingding.delegate;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.widget.InputView;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class PINDelegate extends AppDelegate {

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_pin;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();

    }

    public void setFirstPIN() {
        InputView input = get(R.id.input_pin);
        input.setEmpty();
        Button btn = get(R.id.btn_pin_finish);
        btn.setVisibility(View.VISIBLE);
        TextView describe = get(R.id.tv_pin_describe);
        describe.setText("二：请再次确认叮叮交易密码");

    }

    public void setSecondPin() {
        InputView input = get(R.id.input_pin);
        input.setEmpty();
        Button btn = get(R.id.btn_pin_finish);
        btn.setVisibility(View.GONE);
        TextView describe = get(R.id.tv_pin_describe);
        describe.setText("一：请设置叮叮交易密码");

    }

}
