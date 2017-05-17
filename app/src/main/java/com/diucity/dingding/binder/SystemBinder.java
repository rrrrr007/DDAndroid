package com.diucity.dingding.binder;

import android.util.Log;

import com.diucity.dingding.delegate.SystemDelegate;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Send.ListBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;

import rx.Observer;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class SystemBinder implements DataBinder<SystemDelegate,NormalBack> {
    @Override
    public void viewBindModel(SystemDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(SystemDelegate viewDelegate, Object object) {
        if (object instanceof ListBean) {
            ListBean bean = (ListBean) object;
            Network.subscribe(Network.getApi().notices(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<Object>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(Object s) {
                    Log.d("ch", GsonUtils.GsonString(s));

                }
            });
        }
    }
}
