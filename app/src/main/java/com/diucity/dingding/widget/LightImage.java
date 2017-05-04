package com.diucity.dingding.widget;

import android.content.Context;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

public class LightImage extends android.support.v7.widget.AppCompatImageView {
    public LightImage(Context context) {
        super(context);
    }

    public LightImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LightImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            setAlpha(0.4f);
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            setAlpha(1f);
        }
        return super.onTouchEvent(event);

    }

    // 改变亮度
    private void changeLight(int brightness) {
        ColorMatrix cMatrix = new ColorMatrix();
        cMatrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0, brightness,
                0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });
        this.setColorFilter(new ColorMatrixColorFilter(cMatrix));
    }
}
