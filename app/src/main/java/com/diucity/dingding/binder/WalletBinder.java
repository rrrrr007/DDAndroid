package com.diucity.dingding.binder;

import android.util.Log;

import com.diucity.dingding.delegate.WalletDelegate;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Back.SummaryBack;
import com.diucity.dingding.entity.Send.ListBean;
import com.diucity.dingding.entity.Send.SummaryBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;
import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;

import rx.Observer;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class WalletBinder implements DataBinder<WalletDelegate,NormalBack> {
    @Override
    public void viewBindModel(WalletDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(WalletDelegate viewDelegate, Object object) {
        if (object instanceof SummaryBean) {
            SummaryBean bean = (SummaryBean) object;
            Network.subscribe(Network.getApi().summary(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<SummaryBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(SummaryBack s) {
                    Log.d("ch", GsonUtils.GsonString(s));
                    if (s.getCode()==0){
                        viewDelegate.setText(String.valueOf(s.getData().getIncome()), R.id.tv_wallet_money);
                        work(viewDelegate,new ListBean(bean.getRecycler_id(),bean.getAuth_code(),0,10));
                    }
                }
            });
        }else if (object instanceof ListBean){
            ListBean bean = (ListBean) object;
            Network.subscribe(Network.getApi().list(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<Object>() {

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
