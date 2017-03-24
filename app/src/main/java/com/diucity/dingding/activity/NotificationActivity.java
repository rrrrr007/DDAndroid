package com.diucity.dingding.activity;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.NotificationDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.widget.SwitchView;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCheckedTextView;
import com.jakewharton.rxbinding.widget.RxTextSwitcher;

public class NotificationActivity extends BaseActivity<NotificationDelegate> {


    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected Class getDelegateClass() {
        return NotificationDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        SwitchView switchView = viewDelegate.get(R.id.switch_notification_notify);
        switchView.setLinster(turn -> {
            Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            boolean b = vibrator.hasVibrator();
            Log.d("ch",b+"");
            if (turn)
                viewDelegate.toast("开");
            else {
                viewDelegate.toast("关");
            }
        });
    }
}
