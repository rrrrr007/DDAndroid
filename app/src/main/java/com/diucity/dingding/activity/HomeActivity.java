package com.diucity.dingding.activity;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.HomeDelegate;
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
        RxView.clicks(viewDelegate.get(R.id.ll_home_item1)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,UserInfoActivity.class));
                });
        RxView.clicks(viewDelegate.get(R.id.ll_home_item2)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,SystemActivity.class));
                });
        RxView.clicks(viewDelegate.get(R.id.ll_home_item3)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    showCallDialog();
                });
        RxView.clicks(viewDelegate.get(R.id.ll_home_item3)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,OptionsActivity.class));
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
