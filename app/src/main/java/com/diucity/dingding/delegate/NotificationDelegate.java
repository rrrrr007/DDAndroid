package com.diucity.dingding.delegate;

import android.widget.Switch;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.SpUtils;

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
        return true;
    }

    @Override
    public void initWidget() {
        ((Switch) get(R.id.switch_notification_notify)).setChecked(SpUtils.getBoolean(getActivity(), SpUtils.NOTIFICATION, true));
        ((Switch) get(R.id.switch_notification_sound)).setChecked(SpUtils.getBoolean(getActivity(), SpUtils.SOUND, true));
        ((Switch) get(R.id.switch_notification_vibrate)).setChecked(SpUtils.getBoolean(getActivity(), SpUtils.VIBRATE, true));
    }
}
