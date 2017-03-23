package com.diucity.dingding.binder;

import android.content.Intent;

import com.diucity.dingding.R;
import com.diucity.dingding.activity.Forget2Activity;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.delegate.ForgetDelegate;
import com.diucity.dingding.delegate.LoginDelegate;
import com.diucity.dingding.entity.MessageBack;
import com.diucity.dingding.persent.DataBinder;

import rx.Observer;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class ForgetBinder implements DataBinder<ForgetDelegate, MessageBack> {

    @Override
    public void viewBindModel(ForgetDelegate viewDelegate, MessageBack data) {

    }

    @Override
    public void work(ForgetDelegate viewDelegate, String params) {
        viewDelegate.showLoadingWarn("短信发送中");
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
                viewDelegate.getActivity().startActivity(new Intent(viewDelegate.getActivity(), Forget2Activity.class));
                viewDelegate.getActivity().finish();
            }
        });
    }
}