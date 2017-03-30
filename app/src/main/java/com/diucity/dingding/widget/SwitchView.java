package com.diucity.dingding.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.diucity.dingding.R;
import com.liaoinstan.springview.utils.DensityUtil;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class SwitchView extends View implements View.OnClickListener {
    private Linster linster;

    public void setLinster(Linster linster) {
        this.linster = linster;
    }

    private Context mContext;
    private Paint mPaint;
    private float width, height;
    private boolean isOpen = false;
    private RectF oval;

    public SwitchView(Context context) {
        super(context);
        init(context);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mContext = context;
        //设置宽高
        width = DensityUtil.dip2px(mContext, 60);
        height = width / 2;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setOnClickListener(this);
        oval = new RectF(0, 0, width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //根据控件当前状态设置画笔颜色
        if (isOpen) {
            mPaint.setColor(getResources().getColor(R.color.colorAccent));
        } else {
            mPaint.setColor(Color.GRAY);
        }
        mPaint.setStyle(Paint.Style.FILL);// 充满

        //画底层圆角矩形
        canvas.drawRoundRect(oval, height / 2, width / 4, mPaint);// 第二个参数是x半径，第三个参数是y半径

        //画开关圆圈 将画笔颜色设为白色
        mPaint.setColor(Color.WHITE);
        //根据控件当前状态判断将圆圈画在左边还是右边
        if (isOpen) {
            canvas.drawCircle(width / 4 * 3, height / 2, height / 2 - DensityUtil.dip2px(mContext, 2), mPaint);
        } else {
            canvas.drawCircle(width / 4, height / 2, height / 2 - DensityUtil.dip2px(mContext, 2), mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //重新测量控件的大小，主要在控件宽高属性设置为wrap_content时，将控件大小设置为实际大小
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    //测量宽度
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) DensityUtil.dip2px(mContext, 60) + getPaddingLeft() + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    //测量高度
    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) DensityUtil.dip2px(mContext, 60) / 2 + getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 点击切换isOpen值，调用postInvalidate重绘view,可用于异步线程
     */
    @Override
    public void onClick(View view) {
        isOpen = !isOpen;
        postInvalidate();
        if (linster != null)
            linster.click(isOpen);
    }

    /**
     * 打开控件开关
     */
    public void open() {
        if (!isOpen) {
            isOpen = true;
            postInvalidate();
        }
    }

    /**
     * 关闭控件开关
     */
    public void close() {
        if (isOpen) {
            isOpen = false;
            postInvalidate();
        }
    }

    /**
     * 获取控件状态
     *
     * @return
     */
    public boolean isOpen() {
        return isOpen;
    }

    public interface Linster {
        void click(boolean turn);
    }
}
