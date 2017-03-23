package com.diucity.dingding.utils;

import android.content.Context;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class DensityUtils {
    public static int dip2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dip(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
