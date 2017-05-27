package com.diucity.dingding.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.diucity.dingding.R;
import com.diucity.dingding.utils.ActivityUtils;
import com.liaoinstan.springview.utils.DensityUtil;


/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class SwichBarView extends View {
    private float width;
    private float height;
    private float padding;
    private float offset = 0;

    private Paint outPaint;
    private Paint inPaint;
    private Paint textPaint;

    private RectF oval;

    private Context context;
    private ViewPager vp;

    private String a = "废品报价", b = "回收筐", c = "奖励策略";

    public SwichBarView(Context context) {
        super(context);
        initView(context);
    }

    public SwichBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SwichBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        outPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outPaint.setColor(ContextCompat.getColor(context, R.color.text_green));
        outPaint.setStrokeWidth(DensityUtil.dip2px(context, 1));
        outPaint.setStyle(Paint.Style.STROKE);
        inPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(ActivityUtils.sp2px(context, 14));

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        padding = width * 0.02f;
        oval = new RectF(padding, padding, width - padding, height - padding);
        canvas.drawRoundRect(oval, height / 2, height / 2, outPaint);//外圆

        oval = new RectF(padding + offset, padding, (width - 2 * padding) / 3 + padding + offset, height - padding);
        LinearGradient shader = new LinearGradient(oval.left, oval.top, oval.right, oval.bottom, Color.parseColor("#19b786"), Color.parseColor("#009479"), Shader.TileMode.MIRROR);
        inPaint.setShader(shader);
        canvas.drawRoundRect(oval, height / 2, height / 2, inPaint);//内圆

        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        float baseline = (height - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        textPaint.setColor(ContextCompat.getColor(context, R.color.text_green));
        textPaint.setFakeBoldText(false);
        Rect rect1 = measureText(a);
        canvas.drawText(a, (width - padding * 2) / 6 + padding - rect1.width() / 2, baseline, textPaint);
        Rect rect2 = measureText(b);
        canvas.drawText(b, width / 2 - rect2.width() / 2, baseline, textPaint);
        Rect rect3 = measureText(c);
        canvas.drawText(c, (width - padding * 2) / 6 * 5 + padding - rect3.width() / 2, baseline, textPaint);
        textPaint.setColor(ContextCompat.getColor(context, R.color.selector_white1));
        textPaint.setFakeBoldText(true);
        canvas.clipRect(oval.left, oval.top, oval.right, oval.bottom);
        Rect rect4 = measureText(a);
        canvas.drawText(a, (width - padding * 2) / 6 + padding - rect4.width() / 2, baseline, textPaint);
        Rect rect5 = measureText(b);
        canvas.drawText(b, width / 2 - rect5.width() / 2, baseline, textPaint);
        Rect rect6 = measureText(c);
        canvas.drawText(c, (width - padding * 2) / 6 * 5 + padding - rect6.width() / 2, baseline, textPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            if (x > padding && x < (width - padding * 2) / 3 + padding)
                state(0);
            else if (x > (width - padding * 2) / 3 + padding && x < (width - padding * 2) / 3 * 2 + padding)
                state(1);
            else if (x > (width - padding * 2) / 2 + padding && x < width - padding)
                state(2);
        }
        return true;

    }

    public void state(int i) {
        if (vp != null) vp.setCurrentItem(i);
        switch (i) {
            case 0:
                setOffset(0);
                break;
            case 1:
                setOffset((width - 2 * padding) / 3);
                break;
            case 2:
                setOffset((width - 2 * padding) / 3 * 2);
                break;
        }
    }

    public void connectViewPager(final ViewPager vp) {
        this.vp = vp;
        ((CompatViewPager) vp).setListener((l, t, oldl, oldt) -> {
            float i = l * 1f / vp.getWidth();
            setOffset(i * (width - 2 * padding) / 3);

        });
    }

    private void setOffset(float offset) {
        this.offset = offset;
        invalidate();
    }

    private Rect measureText(String str) {
        Rect mTextBound = new Rect();
        textPaint.getTextBounds(str, 0, str.length(), mTextBound);
        return mTextBound;
    }
}
