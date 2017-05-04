package com.diucity.dingding.widget;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

public class LightText extends android.support.v7.widget.AppCompatTextView {
    public LightText(Context context) {
        super(context);
    }

    public LightText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LightText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            changeLight(-50);
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            changeLight(0);
        }
        return super.onTouchEvent(event);
    }
    private void changeLight(int brightness) {
        ColorMatrix cMatrix = new ColorMatrix();
        cMatrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0, brightness, // 改变亮度
                0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });
        getPaint().setColorFilter(new ColorMatrixColorFilter(cMatrix));
        invalidate();
    }
}
