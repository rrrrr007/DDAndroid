package com.diucity.dingding.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.File;
import java.util.Locale;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class VersonUtils {

    public static int getAppVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo info = packageManager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getAppVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo info = packageManager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getVersion(Context context) {
        String name = getAppVersionName(context);
        String code = String.valueOf(getAppVersionCode(context));
        return name + code;
    }

    public static void notice(final Activity activity, final String url, final versionEvent event, boolean is) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("更新提示");
        if (is) {
            builder.setMessage("低于当前版本,是否更新最新版本(取消将无法使用丢丢)");
        } else {
            builder.setMessage("低于当前版本,是否更新最新版本");
        }
        builder.setNegativeButton("是", (dialogInterface, i) -> {
            dialogInterface.dismiss();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse(url));
            activity.startActivity(intent);
        });
        builder.setPositiveButton("否", (dialogInterface, i) -> {

            Toast.makeText(activity, "取消更新", Toast.LENGTH_SHORT).show();
            dialogInterface.dismiss();
            if (event != null) {
                event.event();
            }


        });
        builder.setCancelable(false);
        builder.create().show();

    }

    /**
     * 安装APk
     *
     * @param file
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 卸载APk
     *
     * @param packName
     */
    public static void uninstallApk(Context context, String packName) {
        Uri uri = Uri.parse("package:" + packName);//获取删除包名的URI
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DELETE);//设置我们要执行的卸载动作
        intent.setData(uri);//设置获取到的URI
        context.startActivity(intent);
    }

    public interface versionEvent {
        void event();
    }

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号 例如：5.0
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号  例如：SM-G9008W
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商 例如：samsung
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }
}
