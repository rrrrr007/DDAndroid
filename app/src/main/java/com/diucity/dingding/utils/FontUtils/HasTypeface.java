package com.diucity.dingding.utils.FontUtils;

import android.graphics.Typeface;

/**
 * There are two ways to set typeface for custom views:
 * <ul>
 * <li>Implementing this interface. You should only implements {@link #setTypeface(Typeface)} method.</li>
 * <li>Or via reflection. If custom view already has setTypeface method you can
 * register it during Calligraphy configuration
 * </ul>
 * First way is faster but encourage more effort from the developer to implements interface. Second one
 * requires less effort but works slowly cause reflection calls.
 *
 * @author Dmitriy Tarasov
 */
public interface HasTypeface {

    void setTypeface(Typeface typeface);

}
