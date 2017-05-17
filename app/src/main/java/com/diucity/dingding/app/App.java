package com.diucity.dingding.app;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;


import com.bugtags.library.Bugtags;
import com.bugtags.library.BugtagsOptions;
import com.diucity.dingding.activity.BaseActivity;
import com.diucity.dingding.utils.FontUtils.CalligraphyConfig;
import com.diucity.dingding.R;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.QbSdk;
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
        QbSdk.initX5Environment(this,null);
        LeakCanary.install(this);
        ZXingLibrary.initDisplayOpinion(this);
        BugtagsOptions options = new BugtagsOptions.Builder().
                trackingLocation(true).//是否获取位置，默认 true
                trackingCrashLog(false).//是否收集crash，默认 true
                trackingConsoleLog(true).//是否收集console log，默认 true
                trackingUserSteps(true).//是否收集用户操作步骤，默认 true
                trackingNetworkURLFilter("(.*)").//自定义网络请求跟踪的 url 规则，默认 null
                build();
        Bugtags.start("43592f597541e3b9b5a900d734aaab66", this, Bugtags.BTGInvocationEventBubble);
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

