package com.diucity.dingding.binder;

import android.content.Intent;

import com.diucity.dingding.R;
import com.diucity.dingding.activity.LoginActivity;
import com.diucity.dingding.activity.PINActivity;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.delegate.LoginDelegate;
import com.diucity.dingding.entity.MessageBack;
import com.diucity.dingding.persent.DataBinder;

import rx.Observer;


/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class LoginBinder implements DataBinder<LoginDelegate, MessageBack> {
    @Override
    public void viewBindModel(LoginDelegate viewDelegate, MessageBack data) {

    }

    @Override
    public void work(LoginDelegate viewDelegate, String params) {
        viewDelegate.showLoadingWarn("正在登录中");
        Network.subscribe(Network.getApi().msg(Network.getMap(params)), new Observer<MessageBack>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                viewDelegate.hideLoadingWarn();
                viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar),2,"网络状态不佳");
            }

            @Override
            public void onNext(MessageBack messageBack) {
                viewDelegate.hideLoadingWarn();
                viewDelegate.getActivity().startActivity(new Intent(viewDelegate.getActivity(), PINActivity.class));
            }
        });
    }
}
