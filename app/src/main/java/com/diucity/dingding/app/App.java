package com.diucity.dingding.app;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.Log;

import com.bugtags.library.Bugtags;
import com.bugtags.library.BugtagsOptions;
import com.diucity.dingding.R;
import com.diucity.dingding.activity.BaseActivity;
import com.diucity.dingding.entity.Back.LoginBack;
import com.diucity.dingding.entity.Back.RequestBack;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.FontUtils.CalligraphyConfig;
import com.diucity.dingding.utils.SpUtils;
import com.google.gson.Gson;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.QbSdk;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2017/3/8 0008.
 */

public class App extends android.app.Application {
    public final static String TAG = "MyApplication";
    public final static boolean DEBUG = true;
    private static App app;
    private static int mainTid;
    public static LoginBack user;
    public static List<BaseActivity> activities;

    public static RequestBack request;

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
                build();

        Bugtags.start("50138096331774a7804fb33c216e6a71", this, Bugtags.BTGInvocationEventBubble,options);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                //.setDefaultFontPath(Environment.getRootDirectory().getPath() + "/fonts/NotoSansOriyaUI-Bold.ttf")//指定字体
                .setFontAttrId(R.attr.fontPath)
                .build());
        JPushInterface.setDebugMode(true);//正式版的时候设置false，关闭调试
        JPushInterface.init(this);
        initDate();
    }

    private void initDate() {
        String user = SpUtils.getString(this, SpUtils.USER, "");
        if (!TextUtils.isEmpty(user)) {
            this.user = new Gson().fromJson(user, LoginBack.class);
        }
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

    private static List<BaseActivity> getActivities() {
        return activities;
    }

    public static BaseActivity getAcitvity(String name){
        List<BaseActivity> activities = getActivities();
        for (BaseActivity activity : activities) {
            Log.d("ch",activity.getLocalClassName());
            if (activity.getLocalClassName().equals(name)){
                return activity;
            }
        }
        return null;
    }

    public static void quiteApplication() {
        clearActivities();
        System.exit(0);
    }
}

