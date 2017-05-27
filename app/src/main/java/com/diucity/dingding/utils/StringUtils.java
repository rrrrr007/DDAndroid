package com.diucity.dingding.utils;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class StringUtils {
    public static String getDoubleString(double b){
        return String.format("%.2f", b);
    }

    public static String getIntString(double b){
        return String.format("%.0f", b);
    }
}
