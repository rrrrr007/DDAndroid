package com.diucity.dingding.delegate;

import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class NotificationDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_notification;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ((TextView) get(R.id.toolbar)).setText("新消息通知");
    }
}
