package com.diucity.dingding.binder;

import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.activity.Forget2Activity;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.delegate.ForgetDelegate;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Send.SmsBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;

import rx.Observer;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class ForgetBinder implements DataBinder<ForgetDelegate, NormalBack> {

    @Override
    public void viewBindModel(ForgetDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(ForgetDelegate viewDelegate, Object object) {
        if (object instanceof SmsBean) {
            SmsBean bean = (SmsBean) object;
            viewDelegate.showLoadingWarn("短信发送中");
            Network.subscribe(Network.getApi().sms(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<NormalBack>() {
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
                public void onNext(NormalBack back) {
                    viewDelegate.hideLoadingWarn();
                    if (back.getCode() == 0) {
                        viewDelegate.startActivity(Forget2Activity.class, "phone", bean.getMobile());
                    } else
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 3, back.getMessage());
                    Log.d("ch", GsonUtils.GsonString(back));
                }
            });
        }

    }
}