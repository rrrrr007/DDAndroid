package com.diucity.dingding.utils;

import android.util.Log;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class TimeUtils {
    public static boolean yesterday(long time){
        Log.d("ch",getDay(time)+"");
        Log.d("ch",getDay(System.currentTimeMillis())+"");
        return getDay(time)<getDay(System.currentTimeMillis());
    }

    public static int getDay(long time){
        SimpleDateFormat myFmt = new SimpleDateFormat("yyMMdd");
        return Integer.parseInt(myFmt.format(time));
    }
}
