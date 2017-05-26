package com.diucity.dingding.binder;

import android.content.Intent;
import android.util.Log;

import com.diucity.dingding.activity.PaymentActivity;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.delegate.CountDelegate;
import com.diucity.dingding.entity.Back.CreateBack;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Back.SupplierBack;
import com.diucity.dingding.entity.Send.CreateBean;
import com.diucity.dingding.entity.Send.SupplierBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rx.Observer;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class CountBinder implements DataBinder<CountDelegate, NormalBack> {
    private String url;
    @Override
    public void viewBindModel(CountDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(CountDelegate viewDelegate, Object object) {
        if (object instanceof CreateBean) {
            viewDelegate.showLoadingWarn("订单生成中");
            CreateBean bean = (CreateBean) object;
            Network.subscribe(Network.getApi().create(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<CreateBack>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    viewDelegate.hideLoadingWarn();
                }

                @Override
                public void onNext(CreateBack o) {
                    viewDelegate.hideLoadingWarn();
                    Log.d("ch", GsonUtils.GsonString(o));
                    if (o.getCode()==0){
                        Intent intent = new Intent(viewDelegate.getActivity(), PaymentActivity.class);
                        intent.putExtra("orderId",o.getData().getOrder_id());
                        intent.putExtra("payId",o.getData().getOrder_id());
                        intent.putExtra("number",o.getData().getOrder_no());
                        intent.putExtra("total",o.getData().getTotal_price());
                        ArrayList<CreateBack.DataBean.ScrapsBean> list = (ArrayList<CreateBack.DataBean.ScrapsBean>) o.getData().getScraps();
                        intent.putExtra("list", list);
                        intent.putExtra("url",url);

                        viewDelegate.startActivity(intent);
                        viewDelegate.finish();
                    }
                }
            });
        }else if (object instanceof SupplierBean){
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
                        url = o.getData().getAvatar();
                        viewDelegate.setUserInfo(o.getData().getName(),o.getData().getAvatar());
                    }
                }
            });
        }
    }
}
