package com.diucity.dingding.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.liaoinstan.springview.widget.SpringView;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class MySpringView extends SpringView {
    private boolean footEnale = true, headEnable = true;

    public MySpringView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = 0;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            y = getScrollY();
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            Log.d("ch",y+"y"+getScrollY()+"sY");
            if (!footEnale) {
                if (y<getScrollY()){
                    onFinishFreshAndLoad();
                    return true;
                }else {
                    return super.onTouchEvent(event);
                }

            }
            if (!headEnable){
                if (y>getScrollY()){
                    onFinishFreshAndLoad();
                    return true;
                }else {
                    return super.onTouchEvent(event);
                }
            }
        }
        return super.onTouchEvent(event);
    }


    public void setFootEnale(boolean footEnale) {
        this.footEnale = footEnale;
        if (footEnale&&headEnable){
            setGive(Give.BOTH);
        }else if (!headEnable&&footEnale){
            setGive(Give.TOP);
        }else if (headEnable&&!footEnale){
            setGive(Give.BOTTOM);
        }else {
            setGive(Give.NONE);
        }
    }

    public void setHeadEnable(boolean headEnable) {
        this.headEnable = headEnable;
        if (footEnale&&headEnable){
            setGive(Give.BOTH);
        }else if (!footEnale&&headEnable){
            setGive(Give.BOTTOM);
        }else if(footEnale&&!headEnable){
            setGive(Give.TOP);
        }else {
            setGive(Give.NONE);
        }
    }

}
