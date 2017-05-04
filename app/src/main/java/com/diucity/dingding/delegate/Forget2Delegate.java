package com.diucity.dingding.delegate;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class Forget2Delegate extends AppDelegate {
    private Observable<Long> observable;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_forget2;
    }

    @Override
    public boolean needShow() {
        return true;
    }

    @Override
    public void initWidget() {
        String phone = getActivity().getIntent().getStringExtra("phone");
        TextView describe = get(R.id.tv_forget2_describe);
        describe.setText(describe.getText().toString()+"\n"+phone);
    }

    public void lineChange(View v, boolean hasFocus){
        View view = null;
        switch (v.getId()){
            case R.id.edt_forget2_code:
                view = get(R.id.view_forget2_1);
                break;
            case R.id.edt_forget2_password:
                view = get(R.id.view_forget2_2);
                break;
        }
        view.setBackgroundColor(hasFocus? Color.parseColor("#009479"):Color.parseColor("#C0CCC8"));
        RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.height=(hasFocus?2:1);
        view.setLayoutParams(params);
    }

    public void textChange(boolean has){
        get(R.id.iv_forget2_icon).setVisibility(has?View.VISIBLE: View.GONE);
    }

    public void clearEdt(){
        ((EditText)get(R.id.edt_forget2_password)).setText("");
    }

    public boolean timer(){
        TextView timer = get(R.id.tv_forget2_timer);
        if (!timer.getText().toString().equals("重新发送"))
            return false;
        timer.setText("60s");
        if (observable==null){
            observable = Observable.interval(1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .limit(60);
        }
        observable.subscribe(num -> {
            if (num == 59)
                timer.setText("重新发送");
            else
                timer.setText((59L - num) + "s");
        });
        return true;
    }
}