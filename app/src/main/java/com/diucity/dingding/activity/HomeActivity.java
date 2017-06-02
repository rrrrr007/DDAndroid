package com.diucity.dingding.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Window;

import com.diucity.dingding.R;
import com.diucity.dingding.app.App;
import com.diucity.dingding.binder.HomeBinder;
import com.diucity.dingding.delegate.HomeDelegate;
import com.diucity.dingding.entity.Send.ScrapsBean;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class HomeActivity extends BaseActivity<HomeDelegate> {
    private LocationManager locationManager;
    private ViewPager vp;
    private AlertDialog alertDialog;
    private AlertDialog alertDialog2;
    private

    static final String[] permission = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,// 写入权限
            Manifest.permission.READ_EXTERNAL_STORAGE, //相机
            Manifest.permission.CAMERA, //相机
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };

    static final String[] permission2 = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,// 写入权限
            Manifest.permission.READ_EXTERNAL_STORAGE, //相机
            Manifest.permission.CAMERA, //相机
    };


    @Override
    public String[] getPermissions() {
        return permission;
    }

    @Override
    public String[] getPermissions2() {
        return permission2;
    }

    @Override
    public void getAllGrantedPermission2() {
        viewDelegate.startAcitityWithAnim(CaptureActivity.class);
    }

    @Override
    public void getAllGrantedPermission() {
        openLoaction();
    }

    @Override
    protected Class<HomeDelegate> getDelegateClass() {
        return HomeDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new HomeBinder();
    }

    @Override
    protected void bindEvenListener() {
        //我的钱包
        RxView.clicks(viewDelegate.get(R.id.iv_home_wallet)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.startActivity(WalletActivity.class);
                });

        //联系客服
        RxView.clicks(viewDelegate.get(R.id.iv_home_call)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    showCallDialog();
                });

        //系统消息
        RxView.clicks(viewDelegate.get(R.id.iv_home_system)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.startActivity(SystemActivity.class);
                });

        //回收
        RxView.clicks(viewDelegate.get(R.id.iv_home_collect)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    permission2();
                });
        //卖
        RxView.clicks(viewDelegate.get(R.id.iv_home_sell)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.startAcitityWithAnim(SellActivity.class);
                });

    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String arg0) {

        }

        @Override
        public void onProviderDisabled(String arg0) {

        }

        @Override
        public void onLocationChanged(Location arg0) {
            // 更新当前经纬度
            String string = "纬度为：" + arg0.getLatitude() + ",经度为："
                    + arg0.getLongitude();
            Log.d("ch", string + ".........");
            Geocoder gc = new Geocoder(HomeActivity.this, Locale.getDefault());
            List<Address> locationList = null;
            try {
                locationList = gc.getFromLocation(arg0.getLatitude(), arg0.getLongitude(), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = locationList.get(0);
            String locality = address.getLocality() + address.getSubLocality();//得到城市名称，比如：北京市
            Log.i("ch", "locality = " + locality + "纬度" + address.getLatitude() + "进度" + address.getLongitude());
            App.latitude = address.getLatitude();
            App.longitude = address.getLongitude();

        }
    };

    @Override
    public void initData() {
        vp = viewDelegate.get(R.id.vp_home);
        binder.work(viewDelegate, new ScrapsBean(App.user.getData().getRecycler_id()));
        permission();
    }


    @Override
    protected void doAction1() {
        binder.work(viewDelegate, new ScrapsBean(App.user.getData().getRecycler_id()));
    }

    public void openLoaction() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean ok = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!ok) {
            showLoacationDialog();
            return;
        }
        if (locationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 1, locationListener);
        } else {
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
            locationManager = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
            locationListener = null;
        }
    }


    public void showCallDialog() {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("联系客服（400-8032039）")
                    .setPositiveButton("拨打", (dialog, which) -> {
                        alertDialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + "400-8032023"));
                        startActivity(intent);
                    })
                    .setNegativeButton("取消", (dialog2, which) -> alertDialog.dismiss()).create();
            Window window = alertDialog.getWindow();
            window.setWindowAnimations(R.style.dialog_style);
        }
        alertDialog.show();
    }

    public void showLoacationDialog() {
        if (alertDialog2 == null) {
            alertDialog2 = new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("打开定位能帮助您更好的使用")
                    .setPositiveButton("去设置", (dialog, which) -> {
                        alertDialog2.dismiss();
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    })
                    .setNegativeButton("放弃", (dialog2, which) -> alertDialog2.dismiss()).create();
            Window window = alertDialog2.getWindow();
            window.setWindowAnimations(R.style.dialog_style);
        }
        alertDialog2.show();
    }

    @Override
    public void onBackPressed() {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle("退出")
                    .setMessage("是否退出叮叮")
                    .setNegativeButton("是", (dialog, which) -> {
                        alertDialog.dismiss();
                        System.exit(0);
                    })
                    .setPositiveButton("否", (dialog2, which) -> alertDialog.dismiss()).create();
            Window window = alertDialog.getWindow();
            window.setWindowAnimations(R.style.dialog_style);
        }
        alertDialog.show();
    }
}
