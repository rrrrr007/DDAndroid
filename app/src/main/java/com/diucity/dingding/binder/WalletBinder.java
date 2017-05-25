package com.diucity.dingding.binder;

import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.app.App;
import com.diucity.dingding.delegate.WalletDelegate;
import com.diucity.dingding.entity.Back.ListBack;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Back.SummaryBack;
import com.diucity.dingding.entity.Send.ListBean;
import com.diucity.dingding.entity.Send.SummaryBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;
import com.diucity.dingding.utils.TimeUtils;

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
                        work(viewDelegate,new ListBean(bean.getRecycler_id(), App.user.getData().getAuth_token(),0,10));
                    }
                }
            });
        }else if (object instanceof ListBean){
            ListBean bean = (ListBean) object;
            Network.subscribe(Network.getApi().list(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<ListBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(ListBack s) {
                    if (s.getCode()==0){
                        if (s.getData().getItems().size()>0)
                        viewDelegate.setText((TimeUtils.getTime(s.getData().getItems().get(0).getTime())+" 收入 ￥"+s.getData().getItems().get(0).getAmount()),R.id.tv_wallet_time);
                    }
                    Log.d("ch", GsonUtils.GsonString(s));
                }
            });
        }
    }
}
