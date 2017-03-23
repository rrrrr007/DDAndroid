package com.diucity.dingding.activity;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.NotificationDelegate;
import com.diucity.dingding.persent.DataBinder;
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
        viewDelegate.get(R.id.switch_notification_notify);
    }
}
