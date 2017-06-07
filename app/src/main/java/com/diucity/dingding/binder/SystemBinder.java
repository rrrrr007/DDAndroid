package com.diucity.dingding.binder;

import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.app.App;
import com.diucity.dingding.delegate.SystemDelegate;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Back.SystemBack;
import com.diucity.dingding.entity.Send.ListBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;

import java.util.List;

import rx.Observer;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class SystemBinder implements DataBinder<SystemDelegate, NormalBack> {
    private int i = 1;

    @Override
    public void viewBindModel(SystemDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(SystemDelegate viewDelegate, Object object) {
        if (object instanceof ListBean) {
            ListBean bean = (ListBean) object;
            Log.d("ch",GsonUtils.GsonString(bean));
            Network.subscribe(Network.getApi().notices(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<SystemBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "网络连接错误");
                }

                @Override
                public void onNext(SystemBack s) {
                    Log.d("ch", GsonUtils.GsonString(s));
                    if (s.getCode() == 103 ){
                        App.loginOut(viewDelegate.getActivity());
                    }
                    if (s.getCode() == 0) {
                        i += 1;
                        List<SystemBack.DataBean.NoticesBean> notices = s.getData().getNotices();
                        viewDelegate.notifyData(notices);
                    } else {
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, s.getMessage());
                    }
                }
            });
        }
    }
}
