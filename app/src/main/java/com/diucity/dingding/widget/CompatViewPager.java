package com.diucity.dingding.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class CompatViewPager extends ViewPager {
    private ScrollListener listener;

    public CompatViewPager(Context context) {
        super(context);
    }

    public CompatViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public ScrollListener getListener() {
        return listener;
    }

    public void setListener(ScrollListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        listener.onScroll(l, t, oldl, oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public interface ScrollListener {
        void onScroll(int l, int t, int oldl, int oldt);
    }

}
