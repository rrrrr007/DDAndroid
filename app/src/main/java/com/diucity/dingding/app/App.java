package com.diucity.dingding.app;

import android.content.Context;
import android.os.Environment;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatDelegate;


import com.diucity.dingding.R;
import com.diucity.dingding.activity.BaseActivity;
import com.diucity.dingding.utils.FontUtils.CalligraphyConfig;
import com.squareup.leakcanary.LeakCanary;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


/**
 * Created by Administrator on 2017/3/8 0008.
 */

public class App extends android.app.Application {
    public final static String TAG = "MyApplication";
    public final static boolean DEBUG = true;
    private static App app;
    private static int mainTid;
    private static int height;

    public static List<BaseActivity> activities;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    public static android.app.Application getContext() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        activities = new LinkedList<>();
        mainTid = android.os.Process.myTid();
        LeakCanary.install(this);
        ZXingLibrary.initDisplayOpinion(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                //.setDefaultFontPath(Environment.getRootDirectory().getPath() + "/fonts/NotoSansOriyaUI-Bold.ttf")//指定字体

                .setFontAttrId(R.attr.fontPath)
                .build());
    }


    public static Context getApplication() {
        return app;
    }

    public static int getMainTid() {
        return mainTid;
    }

    public void addActivity(BaseActivity activity) {
        activities.add(activity);
    }

    public void removeActivity(BaseActivity activity) {
        activities.remove(activity);
    }

    public static void clearActivities() {
        ListIterator<BaseActivity> iterator = activities.listIterator();
        BaseActivity activity;
        while (iterator.hasNext()) {
            activity = iterator.next();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    public static void quiteApplication() {
        clearActivities();
        System.exit(0);
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        App.height = height;
    }
}

