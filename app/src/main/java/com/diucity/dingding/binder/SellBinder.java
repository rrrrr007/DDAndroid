package com.diucity.dingding.binder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.SellAdapter;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.delegate.SellDelegate;
import com.diucity.dingding.entity.Back.DescBack;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Send.DescBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;
import com.diucity.dingding.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class SellBinder implements DataBinder<SellDelegate, NormalBack> {
    @Override
    public void viewBindModel(SellDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(SellDelegate viewDelegate, Object object) {
        if (object instanceof DescBean) {
            DescBean bean = (DescBean) object;
            Network.subscribe(Network.getApi().desc(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<DescBack>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "网络连接出错");
                }

                @Override
                public void onNext(DescBack o) {
                    Log.d("ch", GsonUtils.GsonString(o));
                    if (o.getCode() == 0) {
                        viewDelegate.setText(StringUtils.getDoubleString(o.getData().getTotal_price()), R.id.tv_sell_success_total);
                        viewDelegate.setText(StringUtils.getDoubleString(o.getData().getItemTotal()), R.id.tv_sell_success_price);
                        viewDelegate.setText(StringUtils.getDoubleString(o.getData().getBonusTotal()), R.id.tv_sell_success_award);
                        RecyclerView rv = viewDelegate.get(R.id.rv_sell_success);
                        rv.setLayoutManager(new LinearLayoutManager(viewDelegate.getActivity()));
                        List<DescBack.DataBean.ScrapsBean> list = new ArrayList();
                        List<DescBack.DataBean.ScrapsBean> scraps = o.getData().getScraps();
                        for (DescBack.DataBean.ScrapsBean scrap : scraps) {
                            if (scrap.getPrice() != 0) {
                                list.add(scrap);
                            }
                        }
                        rv.setAdapter(new SellAdapter(viewDelegate.getActivity(), list));
                        viewDelegate.setVisiable(true, R.id.card_sell_success);
                        viewDelegate.setVisiable(false, R.id.card_sell);
                        viewDelegate.setVisiable(false, R.id.tv_sell_wait);
                        viewDelegate.setVisiable(false, R.id.tv_sell_wait2);
                    }
                }
            });
        }
    }
}
