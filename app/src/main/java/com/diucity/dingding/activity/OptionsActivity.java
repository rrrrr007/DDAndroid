package com.diucity.dingding.activity;


import android.support.v7.app.AlertDialog;
import android.view.Window;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.OptionsDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.ClearUtils;
import com.diucity.dingding.utils.SpUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


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
            viewDelegate.startActivity(NotificationActivity.class);
        });
        //重设密码
        RxView.clicks(viewDelegate.get(R.id.rl_options_resetPassword)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
            viewDelegate.startAcitityWithAnim(ResetActivity.class);
        });
        //清空缓存
        RxView.clicks(viewDelegate.get(R.id.rl_options_clearCache)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
            clearCache();
        });
        //版本升级
        RxView.clicks(viewDelegate.get(R.id.rl_options_version)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {

        });
        //关于APP
        RxView.clicks(viewDelegate.get(R.id.rl_options_about)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
            viewDelegate.startActivity(AboutActivity.class);
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

    private void clearCache(){
        viewDelegate.setVisiable(true,R.id.progress_options);
        ClearUtils.clearAppCache(this);
        viewDelegate.setText(ClearUtils.caculateCacheSize(this),R.id.tv_options_cache);
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    viewDelegate.setVisiable(false,R.id.progress_options);
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 1, "清理成功");
                });
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
