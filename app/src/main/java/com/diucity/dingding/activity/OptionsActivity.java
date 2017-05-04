package com.diucity.dingding.activity;


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.Window;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.OptionsDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.ClearUtils;
import com.diucity.dingding.utils.SpUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;


public class OptionsActivity extends BaseActivity<OptionsDelegate> {
    private AlertDialog alertDialog;

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected Class<OptionsDelegate> getDelegateClass() {
        return OptionsDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        //通知开关
        RxView.clicks(viewDelegate.get(R.id.rl_options_notification)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
            startActivity(new Intent(this, NotificationActivity.class));
        });
        //重设密码
        RxView.clicks(viewDelegate.get(R.id.rl_options_resetPassword)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
            startActivity(new Intent(this, ResetActivity.class));
        });
        //忘记密码
        RxView.clicks(viewDelegate.get(R.id.rl_options_forgetPassword)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
            startActivity(new Intent(this, ForgetActivity.class));
        });
        //清空缓存
        RxView.clicks(viewDelegate.get(R.id.rl_options_clearCache)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
            ClearUtils.clearAppCache(this);
            viewDelegate.setText(ClearUtils.caculateCacheSize(this),R.id.tv_options_cache);
        });
        //版本升级
        RxView.clicks(viewDelegate.get(R.id.rl_options_version)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {

        });
        //关于APP
        RxView.clicks(viewDelegate.get(R.id.rl_options_about)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
            startActivity(new Intent(this, AboutActivity.class));
        });
        //功能介绍
        RxView.clicks(viewDelegate.get(R.id.rl_options_function)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {

        });
        //切换账号
        RxView.clicks(viewDelegate.get(R.id.tv_options_change)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
                    showCallDialog();
                });
        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_options_back)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
                    finish();
                });


    }

    public void showCallDialog() {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("是否退出当前账号")
                    .setPositiveButton("确定", (dialog, which) -> {
                        alertDialog.dismiss();
                        //退出
                    })
                    .setNegativeButton("取消", (dialog2, which) -> alertDialog.dismiss()).create();
            Window window = alertDialog.getWindow();
            window.setWindowAnimations(R.style.dialog_style);
        }
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(SpUtils.getBoolean(this,SpUtils.NOTIFICATION,true))
            viewDelegate.setText("已开启",R.id.tv_options_isNotification);
        else
            viewDelegate.setText("未开启",R.id.tv_options_isNotification);

    }
}