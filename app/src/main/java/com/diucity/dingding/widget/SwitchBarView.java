package com.diucity.dingding.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.diucity.dingding.utils.ActivityUtils;
import com.diucity.dingding.R;
import com.liaoinstan.springview.utils.DensityUtil;

/**
 * Created by Administrator on 2017/4/11 0011.
 */

public class SwitchBarView extends View {
    private Paint outPaint;
    private Paint inPaint;
    private Paint textPaint;
    private int type = 1;
    private int target = 1;
    private RectF oval;
    private String a = "废品报价", b = "回收筐", c = "奖励策略";
    private float width, height;
    private BarAnimation anim;
    private Listener listener;
    private float offset;
    private float padding;

    public SwitchBarView(Context context) {
        super(context);
        init(context);
    }

    public SwitchBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwitchBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        outPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outPaint.setColor(ContextCompat.getColor(context,R.color.text_green));
        outPaint.setStrokeWidth(DensityUtil.dip2px(context, 1));
        outPaint.setStyle(Paint.Style.STROKE);
        inPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        inPaint.setColor(ContextCompat.getColor(context,R.color.text_green));
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(ActivityUtils.sp2px(context, 14));
        textPaint.setColor(ContextCompat.getColor(context,R.color.text_green));
        anim = new BarAnimation();
        anim.setDuration(100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
         padding = width*0.02f;
        oval = new RectF(0 + padding, 0 + padding, width - padding, height - padding);
        canvas.drawRoundRect(oval, height / 2, height / 2, outPaint);// 第二个参数是x半径，第三个参数是y半径
        if (type == 1) {
            oval = new RectF(0 + padding+offset, 0 + padding, (width - padding*2) / 3 + padding+offset, height - padding);
            canvas.drawRoundRect(oval, height / 2, height / 2, inPaint);
        } else if (type == 2) {
            oval = new RectF((width - padding*2) / 3 + padding+offset, 0 + padding, (width - padding*2) / 3 * 2 + padding+offset, height - padding);
            canvas.drawRoundRect(oval, height / 2, height / 2, inPaint);
        } else {
            oval = new RectF((width - padding*2) / 3 * 2 + padding+offset, 0 + padding, width - padding+offset, height - padding);
            canvas.drawRoundRect(oval, height / 2, height / 2, inPaint);
        }
        Rect bounds = new Rect();
        textPaint.getTextBounds(a, 0, a.length(), bounds);
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        float baseline = (height - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        textPaint.setColor(target == 1 ? Color.parseColor("#ffffff") : Color.parseColor("#009479"));
        textPaint.setFakeBoldText(target==1);
        canvas.drawText(a, (width - padding*2) / 6 + padding - bounds.width() / 2, baseline, textPaint);
        textPaint.setColor(target == 3 ? Color.parseColor("#ffffff") : Color.parseColor("#009479"));
        textPaint.setFakeBoldText(target==3);
        canvas.drawText(c, (width - padding*2) / 6 * 5 + padding - bounds.width() / 2, baseline, textPaint);
        textPaint.getTextBounds(b, 0, b.length(), bounds);
        textPaint.setColor(target == 2 ? Color.parseColor("#ffffff") : Color.parseColor("#009479"));
        textPaint.setFakeBoldText(target==2);
        canvas.drawText(b, (width - padding*2) / 2 + padding - bounds.width() / 2, baseline, textPaint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            if (x > padding && x < (width - padding*2) / 3 + padding)
                setType(1);
            else if (x > (width - padding*2) / 3 + padding && x < (width - padding*2) / 3 * 2 + padding)
                setType(2);
            else if (x > (width - padding*2) / 2 + padding && x < width - padding)
                setType(3);
        }
        return true;

    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setType(int i) {
        if (i > 3 || i < 1) {
            return;
        }
        if (i == type) {
            return;
        }
        target =i;
        type = i;
        invalidate();
        if (listener!=null)
            listener.choice(i);
    }

    public void startAnim(int i){
        if (i > 3 || i < 1) {
            return;
        }
        if (i == type) {
            return;
        }
        target=i;
        this.startAnimation(anim);

    }

    public interface Listener{
        void choice(int i);
    }

    public class BarAnimation extends Animation {
        public BarAnimation() {
        }
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            if (interpolatedTime < 1.0f) {
                if (target>type){
                    offset=(width-padding*2)/3*interpolatedTime;
                }else {
                    offset=-(width-padding*2)/3*interpolatedTime;
                }
                postInvalidate();
            } else {
                offset=0;
                setType(target);
            }
        }
    }
}
