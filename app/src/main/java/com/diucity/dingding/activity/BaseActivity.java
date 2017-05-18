package com.diucity.dingding.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;

import com.bugtags.library.Bugtags;
import com.diucity.dingding.app.App;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.persent.DataBindActivity;
import com.diucity.dingding.persent.IDelegate;
import com.diucity.dingding.utils.ActivityUtils;
import com.diucity.dingding.utils.FontUtils.CalligraphyContextWrapper;
import com.diucity.dingding.utils.PermissUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public abstract class BaseActivity<T extends IDelegate> extends DataBindActivity<T> {
    protected final String TAG = getClass().getSimpleName();
    public BaseActivity activity;
    protected List<Subscription> subscriptions;
    public static final int PERMISSION_DENIEG = 1;//权限不足，权限被拒绝的时候
    public static final int PERMISSION_REQUEST_CODE = 0;//系统授权管理页面时的结果参数
    public static final String PACKAGE_URL_SCHEME = "package:";//权限方案



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscriptions = new ArrayList<>();
        activity = this;
        ((App) ActivityUtils.getContext()).addActivity(this);
        checkNet();
    }

    public void isShowSmallWarn(boolean is) {
        if (is) {
            //show
            ((AppDelegate) viewDelegate).showSmallWarn();
        } else {
            //noshow
            ((AppDelegate) viewDelegate).hideSmallWarn();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
        Bugtags.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Bugtags.onDispatchTouchEvent(this, ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        ((App) ActivityUtils.getContext()).removeActivity(this);
        for (Subscription subscription : subscriptions) {
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
        subscriptions = null;
        super.onDestroy();
    }

    //显示对话框提示用户缺少权限
    public void showMissingPermissionDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");//提示帮助
        builder.setMessage("缺少权限");
        //如果是拒绝授权，则退出应用
        //退出
        builder.setNegativeButton("取消", (dialog, which) ->{
                    dialog.dismiss();
        });
        //打开设置，让用户选择打开权限
        builder.setPositiveButton("设置", (dialog, which) -> {
            dialog.dismiss();
            startAppSettings();//打开设置
        });
        //builder.setCancelable(false);
        builder.show();
    }

    //打开系统应用设置(ACTION_APPLICATION_DETAILS_SETTINGS:系统设置权限)
    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }

    public void getAllGrantedPermission() {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PERMISSION_REQUEST_CODE == requestCode && hasAllPermissionGranted(grantResults)) //判断请求码与请求结果是否一致
        {
            getAllGrantedPermission();
        } else {
            showMissingPermissionDialog();//dialog
        }
    }

    //获取全部权限
    public boolean hasAllPermissionGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    public void requestPermissions(String... permission) {
        ActivityCompat.requestPermissions(this, permission, PERMISSION_REQUEST_CODE);
    }

    public String[] getPermissions() {
        return null;
    }

    protected void permission() {
        if (getPermissions() != null) {
            if (PermissUtils.permissionSet(this, getPermissions())) {
                requestPermissions(getPermissions());     //去请求权限
            } else {
                getAllGrantedPermission();
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void checkNet() {
        ConnectivityManager connectMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
            ((AppDelegate) viewDelegate).showSmallWarn();
        }
    }
}
