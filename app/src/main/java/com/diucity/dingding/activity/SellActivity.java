package com.diucity.dingding.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.diucity.dingding.R;
import com.diucity.dingding.app.App;
import com.diucity.dingding.binder.SellBinder;
import com.diucity.dingding.delegate.SellDelegate;
import com.diucity.dingding.entity.Send.DescBean;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SellActivity extends BaseActivity<SellDelegate> {
    private LocationManager locationManager;
    static final String[] PERMISSION = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };

    @Override
    protected Class<SellDelegate> getDelegateClass() {
        return SellDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new SellBinder();
    }

    @Override
    public String[] getPermissions() {
        return PERMISSION;
    }

    @Override
    public void getAllGrantedPermission() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean ok = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!ok) {
            viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "定位功能未打开");
            viewDelegate.setText("点我设置", R.id.tv_sell_location);
            return;
        }
        if (locationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 1, locationListener);
        } else {
            viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "定位失败");
            viewDelegate.setText("定位失败", R.id.tv_sell_location);
        }
    }

    @Override
    protected void bindEvenListener() {
        //返回
        RxView.clicks(viewDelegate.get(R.id.tv_sell_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.finish();
                    if (viewDelegate.get(R.id.card_sell_success).getVisibility() == View.VISIBLE) {
                        ((HomeActivity) (App.getAcitvity("activity.HomeActivity"))).notifyBasket();
                    }

                });
        RxView.clicks(viewDelegate.get(R.id.tv_sell_location)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                });
        RxView.clicks(viewDelegate.get(R.id.btn_sell_success)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.finish();
                    ((HomeActivity) (App.getAcitvity("activity.HomeActivity"))).notifyBasket();
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
            Geocoder gc = new Geocoder(SellActivity.this, Locale.getDefault());
            List<Address> locationList = null;
            try {
                locationList = gc.getFromLocation(arg0.getLatitude(), arg0.getLongitude(), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = locationList.get(0);
            String locality = address.getLocality() + address.getSubLocality();//得到城市名称，比如：北京市
            Log.i("ch", "locality = " + locality);
            viewDelegate.setText(locality, R.id.tv_sell_location);
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        permission();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
            locationManager = null;
        }
    }

    public void showSuccess() {
        binder.work(viewDelegate, new DescBean(App.user.getData().getRecycler_id()));
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.stay, R.anim.over);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
            locationListener = null;
        }
    }

}


