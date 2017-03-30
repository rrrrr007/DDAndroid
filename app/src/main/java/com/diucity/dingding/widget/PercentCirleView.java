package com.diucity.dingding.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.liaoinstan.springview.utils.DensityUtil;

import org.w3c.dom.Text;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class PercentCirleView extends View {
    private Paint out;
    private Paint in;
    private Paint text;
    private float degree =0;
    private int percent =0;

    public void setPercent(int percent) {
        this.percent = percent;
        this.degree = percent*3.6f;
        invalidate();
    }



    public PercentCirleView(Context context) {
        super(context);
        init(context);
    }

    public PercentCirleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PercentCirleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        out = new Paint(ANTI_ALIAS_FLAG);
        in = new Paint(ANTI_ALIAS_FLAG);
        text = new Paint(ANTI_ALIAS_FLAG);
        out.setColor(Color.GREEN);
        in.setColor(Color.GRAY);
        out .setStrokeWidth(20);
        in .setStrokeWidth(10);
        out.setStyle(Paint.Style.STROKE);
        in.setStyle(Paint.Style.STROKE);
        out.setStrokeCap(Paint.Cap.ROUND);
        text.setColor(Color.BLACK);
        text.setTextAlign(Paint.Align.LEFT);
        text.setTextSize(DensityUtil.dip2px(context,15));


    }

    @Override
    protected void onDraw(Canvas canvas) {
        String content = String.valueOf(percent)+"%";
        if (percent>=100)
            out.setColor(Color.YELLOW);
        int width = getWidth();
        int height = getHeight();
        Log.d("ch","w"+width+"h"+height);
        RectF oval=new RectF();

        if(width>height){
            oval.left=width/2f-height/2f*0.8f;
            oval.top=height/2f*0.2f;
            oval.right =width/2f+height/2f*0.8f;
            oval.bottom = height-height/2f*0.2f;
        }else {
            oval.left=width/2f*0.2f;
            oval.top=height/2f-width/2f*0.8f;
            oval.right = width-width/2f*0.2f;
            oval.bottom = height/2f+width/2f*0.8f;
        }
        canvas.drawArc(oval, -90, 360, false, in);
        canvas.drawArc(oval,-90,degree,false,out);
        Rect bounds = new Rect();
        text.getTextBounds(content, 0, content.length(), bounds);
        Paint.FontMetricsInt fontMetrics = text.getFontMetricsInt();
        int baseline = (height - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(content,width/2-bounds.width()/2,baseline,text);
    }
}
