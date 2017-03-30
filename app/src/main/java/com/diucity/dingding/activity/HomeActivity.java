package com.diucity.dingding.activity;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.HomeDelegate;
import com.diucity.dingding.delegate.SellDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

public class HomeActivity extends BaseActivity<HomeDelegate> {
    private Dialog call;

    @Override
    protected Class<HomeDelegate> getDelegateClass() {
        return HomeDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        //个人中心
        RxView.clicks(viewDelegate.get(R.id.ll_home_item1)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,UserInfoActivity.class));
                });
        //我的钱包
        RxView.clicks(viewDelegate.get(R.id.ll_home_wallet)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this, WalletActivity.class));
                });
        //任务列表
        RxView.clicks(viewDelegate.get(R.id.ll_home_mission)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,MissionActivity.class));
                });
        //账单明细
        RxView.clicks(viewDelegate.get(R.id.ll_home_record)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,RecordActivity.class));
                });
        //收益统计
        RxView.clicks(viewDelegate.get(R.id.ll_home_statistics)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,StatisticsActivity.class));
                });
        //联系客服
        RxView.clicks(viewDelegate.get(R.id.ll_home_call)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    showCallDialog();
                });
        //设置
        RxView.clicks(viewDelegate.get(R.id.ll_home_options)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,OptionsActivity.class));
                });
        //回收
        RxView.clicks(viewDelegate.get(R.id.tv_home_collect)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this, CaptureActivity.class));
                });
        //卖
        RxView.clicks(viewDelegate.get(R.id.tv_home_sell)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,SellActivity.class));
                });
        //今天价格
        RxView.clicks(viewDelegate.get(R.id.tv_home_price)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,PriceActivity.class));
                });
        //动态
        RxView.clicks(viewDelegate.get(R.id.tv_home_dynamic)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,MissionActivity.class));
                });

    }
    private void showCallDialog() {
        if (call == null) {
            View view = getLayoutInflater().inflate(R.layout.dialog_home_call, null);
            RxView.clicks(view.findViewById(R.id.dialog_tv_home_cancel)).throttleFirst(2, TimeUnit.SECONDS)
                    .subscribe(aVoid -> {
                        call.dismiss();
                    });
            RxView.clicks(view.findViewById(R.id.dialog_tv_home_call)).throttleFirst(2, TimeUnit.SECONDS)
                    .subscribe(aVoid -> {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + "400-8032023"));
                        startActivity(intent);
                    });
            call = new Dialog(this, R.style.dialog);
            call.setContentView(view);
            Window dialogWindow = call.getWindow();
            dialogWindow.setGravity(Gravity.CENTER);
        }
        call.show();
    }
}
