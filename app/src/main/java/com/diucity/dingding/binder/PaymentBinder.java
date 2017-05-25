package com.diucity.dingding.binder;

import android.util.Log;
import android.widget.Toast;

import com.diucity.dingding.activity.YWTActivity;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.app.App;
import com.diucity.dingding.delegate.PaymentDelegate;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Back.RequestBack;
import com.diucity.dingding.entity.Send.RequestBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;

import rx.Observer;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class PaymentBinder implements DataBinder<PaymentDelegate, NormalBack> {
    @Override
    public void viewBindModel(PaymentDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(PaymentDelegate viewDelegate, Object object) {
        if (object instanceof RequestBean) {
            RequestBean bean = (RequestBean) object;
            Network.subscribe(Network.getApi().request(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<RequestBack>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(RequestBack o) {
                    if (o.getCode() == 0) {
                        App.request = o;
                        Toast.makeText(viewDelegate.getActivity(), "获取订单", Toast.LENGTH_SHORT).show();
                        viewDelegate.startActivity(YWTActivity.class);
                    }
                    Log.d("ch", GsonUtils.GsonString(o));
                }
            });
        }else if (object instanceof RequestBean){

        }
    }
}
