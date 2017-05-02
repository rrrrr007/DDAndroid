package com.diucity.dingding.utils.FontUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chris on 09/11/14.
 * For Calligraphy.
 */
interface CalligraphyActivityFactory {

    View onActivityCreateView(View parent, View view, String name, Context context, AttributeSet attrs);
}
