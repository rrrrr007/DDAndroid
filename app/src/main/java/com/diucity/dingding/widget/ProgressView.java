package com.diucity.dingding.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class ProgressView extends View {
    private Paint paint;
    private int progress =0;
    public ProgressView(Context context) {
        super(context);
        init(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#009479"));
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float height = getHeight();
        float width = getWidth();
        RectF oval = new RectF(0,0,width*progress/100,height);
        canvas.drawRect(oval, paint);
    }

    public void setProgress(int progress) {
        if (progress>100&&progress<0)
            return;
        if (progress==100){
            new Handler().postDelayed(() -> ProgressView.this.setProgress(0),1000);
        }
        if (progress<this.progress){
            return;
        }
        this.progress = progress;
        invalidate();
    }

    public void reset(){
        this.progress = 0;
        invalidate();
    }
}
