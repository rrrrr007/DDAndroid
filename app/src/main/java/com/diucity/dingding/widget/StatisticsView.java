package com.diucity.dingding.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class StatisticsView extends View {
    private String first,second,third;
    private Paint textPaint;
    private int max;

    public StatisticsView(Context context) {
        super(context);
        init(context);
    }

    public StatisticsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StatisticsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context){
        textPaint = new Paint();
        first = "500";
        second = "300";
        third = "800";
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        //canvas.drawText();

    }
}
