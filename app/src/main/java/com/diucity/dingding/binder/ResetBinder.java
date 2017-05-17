package com.diucity.dingding.binder;

import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.delegate.ResetDelegate;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Send.ChangeBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;

import rx.Observer;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class ResetBinder implements DataBinder<ResetDelegate, NormalBack> {
    @Override
    public void viewBindModel(ResetDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(ResetDelegate viewDelegate, Object object) {
        if (object instanceof ChangeBean) {
            ChangeBean bean = (ChangeBean) object;
            viewDelegate.showLoadingWarn("修改密码中");
            Network.subscribe(Network.getApi().change(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<NormalBack>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    viewDelegate.hideLoadingWarn();
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "网络连接出错");
                }

                @Override
                public void onNext(NormalBack o) {
                    Log.d("ch", GsonUtils.GsonString(o));
                }
            });
        }
    }
}