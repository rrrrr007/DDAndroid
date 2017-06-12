package com.diucity.dingding.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.BaseAdapter;
import com.diucity.dingding.utils.ActivityUtils;

import static android.R.attr.windowBackground;
import static android.support.v7.appcompat.R.color.abc_background_cache_hint_selector_material_light;
import static android.support.v7.appcompat.R.drawable.abc_dialog_material_background;


/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class SectionDecoration extends RecyclerView.ItemDecoration {

    private Context context;
    private TextPaint textPaint;
    private Paint paint;
    private int topGap;
    private int y;
    private boolean single;
    private String str;
    private String str2;

    public SectionDecoration(Context context) {
        this.context = context;
        paint = new Paint();
        TypedArray array = context.getTheme().obtainStyledAttributes(new int[] {
                android.R.attr.colorBackground,
        });
        int backgroundColor = array.getColor(0, 0xFF00FF);
        array.recycle();
        paint.setColor(backgroundColor);
        //设置悬浮栏中文本的画笔
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(ActivityUtils.sp2px(context, 13));
        textPaint.setColor(Color.parseColor("#606664"));
        topGap = ActivityUtils.dip2px(context,34);
        y = topGap;
        single = true;

    }

    public SectionDecoration(Context context, String str) {
        //设置悬浮栏的画笔---paint
        this(context);
        this.str = str;
    }

    public SectionDecoration(Context context, String str,String str2) {
        this(context,str);
        this.str2 = str2;
        single = false;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        if (single) {
            if (pos == 0) {
                outRect.top = topGap;
            } else {
                outRect.top = 0;
            }
        } else {
            if (pos == 0 || pos == 2) {
                outRect.top = topGap;
            } else {
                outRect.top = 0;
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        if (childCount <= 0) return;
        if (!single) {
            for (int i = 0; i < childCount; i++) {
                View view = parent.getChildAt(i);
                int position = parent.getChildAdapterPosition(view);
                if (position == 0 || position == 2) {
                    float top = view.getTop();
                    float bottom = view.getTop();
                    Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
                    float baseline = (bottom - top - topGap - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top + top+15;
                    c.drawText(position == 0 ? str : str2, ActivityUtils.dip2px(context, 25), baseline, textPaint);
                }
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        if (childCount <= 0) return;
        if (single) {
            if (TextUtils.isEmpty(str)){
                str = getString(parent);
            }
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
            float baseline = (topGap - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top+15;
            c.drawRect(left, 0, right, topGap - y, paint);
            c.drawText(str, ActivityUtils.dip2px(context, 25), baseline, textPaint);
        } else {
            String s = str;
            for (int i = 0; i < childCount; i++) {
                View view = parent.getChildAt(i);
                int position = parent.getChildAdapterPosition(view);
                if (position == 1 && position + 1 < childCount && view.getBottom() <= topGap) {
                    y = view.getBottom();
                    break;
                } else if (position == 1 && position + 1 < childCount && view.getBottom() >= topGap) {
                    y = topGap;
                    break;
                } else if (position == 2 ) {
                    y = topGap;
                    s =str2;
                    break;
                }
            }
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
            float baseline = (topGap - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top - topGap + y+15;
            c.drawRect(left, 0, right, y, paint);
            c.drawText(s, ActivityUtils.dip2px(context, 25), baseline, textPaint);
        }
    }

    private String getString(RecyclerView rv){
        return ((BaseAdapter)rv.getAdapter()).decorrationString();
    }
}
