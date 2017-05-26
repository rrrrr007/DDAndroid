package com.diucity.dingding.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class TimeUtils {
    public static boolean yesterday(long time) {
        Log.d("ch", getDay(time) + "");
        Log.d("ch", getDay(System.currentTimeMillis()) + "");
        return getDay(time) < getDay(System.currentTimeMillis());
    }

    public static int getDay(long time) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyMMdd");
        return Integer.parseInt(myFmt.format(time));
    }

    public static String getTime(long time) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");
        return myFmt.format(time);
    }

    public static String getMinute(long time) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return myFmt.format(time);
    }


    public static String getHours(long time) {
        SimpleDateFormat myFmt = new SimpleDateFormat("MM-dd");
        return myFmt.format(time);
    }

    public static String getMonth(long time) {

        SimpleDateFormat myFmt = new SimpleDateFormat("MM");

        int m = Integer.parseInt(myFmt.format(System.currentTimeMillis())) - Integer.parseInt(myFmt.format(time));
        if (m == 0) {
            return "本月";
        } else {
            switch (myFmt.format(time)) {
                case "01":
                    return "一月";
                case "02":
                    return "二月";
                case "03":
                    return "三月";
                case "04":
                    return "四月";
                case "05":
                    return "五月";
                case "06":
                    return "六月";
                case "07":
                    return "七月";
                case "08":
                    return "八月";
                case "09":
                    return "九月";
                case "10":
                    return "十月";
                case "11":
                    return "十一月";
                case "12":
                    return "十二月";
            }
        }
        return "";
    }

    public static String getWeek(long time) {


        String Week = "";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {

            c.setTime(format.parse(format.format(time)));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            Week += "周日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            Week += "周一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            Week += "周二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            Week += "周三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            Week += "周四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            Week += "周五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            Week += "周六";
        }


        return Week;
    }


}
