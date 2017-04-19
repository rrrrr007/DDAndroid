package com.diucity.dingding.recevier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


import com.diucity.dingding.app.App;
import com.diucity.dingding.activity.BaseActivity;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class NetworkRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
            Log.i("ch", "unconnect");
            // unconnect network
        }else {
            Log.i("ch", "connect");
            // connect network
        }
        for (BaseActivity activity : App.activities) {
            activity.isShowSmallWarn(!mobNetInfo.isConnected() && !wifiNetInfo.isConnected());
        }
    }
}
