package com.diucity.dingding.binder;

import android.content.Intent;
import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.activity.CaptureActivity;
import com.diucity.dingding.activity.CountActivity;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.delegate.CaptureDelegate;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Back.SupplierBack;
import com.diucity.dingding.entity.Send.SupplierBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;

import rx.Observer;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class CaptureBinder implements DataBinder<CaptureDelegate, NormalBack> {

    @Override
    public void viewBindModel(CaptureDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(CaptureDelegate viewDelegate, Object object) {
        if (object instanceof SupplierBean){
            SupplierBean bean = (SupplierBean) object;
            Network.subscribe(Network.getApi().supplier(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<SupplierBack>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(SupplierBack o) {
                    Log.d("ch", GsonUtils.GsonString(o));
                    if (o.getCode()==0){
                        Intent intent = new Intent(viewDelegate.getActivity(), CountActivity.class);
                        intent.putExtra("url",o.getData().getAvatar());
                        intent.putExtra("name",o.getData().getName());
                        intent.putExtra("value", o.getData().getSupplier_id());
                        viewDelegate.startActivity(intent);
                        viewDelegate.finish();
                    }else {
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, o.getMessage());
                    }
                }
            });
        }
    }
}
