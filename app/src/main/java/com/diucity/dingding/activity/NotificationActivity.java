package com.diucity.dingding.activity;



import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.SpUtils;
import com.diucity.dingding.R;
import com.diucity.dingding.delegate.NotificationDelegate;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import java.util.concurrent.TimeUnit;


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
        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_notify_back)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
                    finish();
                });

        //开关
        RxCompoundButton.checkedChanges(viewDelegate.get(R.id.switch_notification_notify)).subscribe(aBoolean -> {
                    SpUtils.putBoolean(this,SpUtils.NOTIFICATION,aBoolean);
                });
        RxCompoundButton.checkedChanges(viewDelegate.get(R.id.switch_notification_sound)).subscribe(aBoolean -> {
                    SpUtils.putBoolean(this,SpUtils.SOUND,aBoolean);
                });
        RxCompoundButton.checkedChanges(viewDelegate.get(R.id.switch_notification_vibrate)).subscribe(aBoolean -> {
                    SpUtils.putBoolean(this,SpUtils.VIBRATE,aBoolean);
                });
    }

    @Override
    public void initData() {
        super.initData();



    }
}
