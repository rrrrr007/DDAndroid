package com.diucity.dingding.delegate;

import android.graphics.Color;
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

    public void lineChange(View v, boolean hasFocus){
        View view = null;
        switch (v.getId()){
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
        view.setBackgroundColor(hasFocus? Color.parseColor("#009479"):Color.parseColor("#C0CCC8"));
        RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.height=(hasFocus?2:1);
        view.setLayoutParams(params);
    }

    public void textChange(int i ,boolean has){
        get(i).setVisibility(has? View.VISIBLE: View.GONE);
    }

    public void clearEdt(){
        ((EditText)get(R.id.edt_forget2_password)).setText("");
    }
}
