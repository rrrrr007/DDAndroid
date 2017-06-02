package com.diucity.dingding.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class PermissUtils {
    public static boolean permissionSet(Context context, String... permissions) {
        for (String permission : permissions) {
            if (isLackPermission(permission, context)) {//是否添加完全部权限集合
                Log.d("ch", permission);
                return true;
            }
        }
        return false;
    }

    //检查系统权限是，判断当前是否缺少权限(PERMISSION_DENIED:权限是否足够)
    private static boolean isLackPermission(String permission, Context context) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;
    }
}
