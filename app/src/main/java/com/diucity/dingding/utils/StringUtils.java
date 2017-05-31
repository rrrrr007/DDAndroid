package com.diucity.dingding.utils;

import java.text.DecimalFormat;

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

    public static String  fmoney(double d ,int n ){
        n = n > 0 && n <= 20 ? n : 2;
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<n; i++){
            sb.append("0");
        }
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("##,###."+sb.toString());
        String format = myformat.format(d);
        if (format.startsWith(".")){
            format="0"+format;
        }
        return format;
    }
}
