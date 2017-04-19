package com.diucity.dingding.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Window;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.HomeDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.widget.SwitchBarView;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

public class HomeActivity extends BaseActivity<HomeDelegate> implements ViewPager.OnPageChangeListener {
    private SwitchBarView sbv;
    private ViewPager vp;
    private AlertDialog alertDialog;

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
                    viewDelegate.startActivity(CaptureActivity.class);
                });
        //卖
        RxView.clicks(viewDelegate.get(R.id.iv_home_sell)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.startActivity(SellActivity.class);
                });

        //vp滑动监听
        vp.addOnPageChangeListener(this);

        //选择器
        sbv.setListener(i -> {
            vp.setCurrentItem(i - 1);
        });

    }

    @Override
    public void initData() {
        sbv = viewDelegate.get(R.id.sbv_home);
        vp = viewDelegate.get(R.id.vp_home);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        sbv.startAnim(position + 1);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    public void showCallDialog() {
        if (alertDialog==null){
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

}
