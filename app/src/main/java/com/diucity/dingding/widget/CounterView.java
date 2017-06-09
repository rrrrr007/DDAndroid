package com.diucity.dingding.widget;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/4/5
 */

public class CounterView extends android.support.v7.widget.AppCompatTextView {

    public static final String DEFAULT_INT_FORMAT = getDecimalFormat(0);

    private static final int DEFAULT_DURATION = 1000;

    private int mDuration = DEFAULT_DURATION;

    private float mNumber;

    private String mFormat;

    private TimeInterpolator interpolator;

    private OnUpdateListener mOnUpdateListener;

    public interface OnUpdateListener{
        boolean onUpdate(float curValue);
    }

    public CounterView(Context context) {
        this(context,null);
    }

    public CounterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CounterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        interpolator = new AccelerateDecelerateInterpolator();

    }

    public OnUpdateListener getOnUpdateListener() {
        return mOnUpdateListener;
    }

    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
        this.mOnUpdateListener = onUpdateListener;
    }

    public void setInterpolator(TimeInterpolator interpolator){
        this.interpolator = interpolator;
    }

    public void showAnimation(float number){
        showAnimation(0,number,mDuration,mFormat);
    }

    public void showAnimation(float number,String format){
        showAnimation(0,number,mDuration,format);
    }

    public void showAnimation(float from,float to,int duration){
        showAnimation(from,to,duration,mFormat);
    }

    public void showAnimation(float number,int duration,String format){
        showAnimation(0,number,duration,format);
    }

    public void showAnimation(float from,float to,int duration,String format){
        this.mNumber = to;
        this.mDuration = duration;
        this.mFormat = (format == null ? DEFAULT_INT_FORMAT : format);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from,to);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(interpolator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float curValue = (float)animation.getAnimatedValue();

                if(mOnUpdateListener==null || !mOnUpdateListener.onUpdate(curValue)){
                    updateNumber(curValue);
                }

            }
        });
        valueAnimator.start();
    }

    public void setFormat(int decimals){
        this.mFormat = getDecimalFormat(decimals);
    }

    /**
     *
     * @param format  default %1$.0f
     */
    public void setFormat(String format){
        this.mFormat = format;
    }

    public void setDuration(int duration){
        this.mDuration = duration;
    }

    private void updateNumber(float number){
        updateNumber(number,mFormat);
    }

    private void updateNumber(float number,String format){
        setText(String.format(format,number));
    }

    /**
     *
     * @param decimals
     * @return  "%1$." + decimals + "f"
     */
    public static String getDecimalFormat(int decimals){
        return "%1$." + decimals + "f";
    }

}
