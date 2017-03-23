package com.diucity.dingding.binder;

import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.delegate.SystemDelegate;
import com.diucity.dingding.entity.MessageBack;
import com.diucity.dingding.persent.DataBinder;

import rx.Observer;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class SystemBinder implements DataBinder<SystemDelegate, MessageBack> {
    @Override
    public void viewBindModel(SystemDelegate viewDelegate, MessageBack data) {
        viewDelegate.notifyData();
    }

    @Override
    public void work(SystemDelegate viewDelegate, String params) {
        Network.subscribe(Network.getApi().msg(Network.getMap(params)), new Observer<MessageBack>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "网络状态不佳");
                viewDelegate.onFinishLoad();
            }

            @Override
            public void onNext(MessageBack messageBack) {
                Log.d("ch",messageBack.getResult().getMessage());
                viewBindModel(viewDelegate, messageBack);
                viewDelegate.onFinishLoad();
            }
        });
    }
}
