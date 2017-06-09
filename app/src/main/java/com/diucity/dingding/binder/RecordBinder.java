package com.diucity.dingding.binder;

import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.app.App;
import com.diucity.dingding.delegate.RecordDelegate;
import com.diucity.dingding.entity.Back.ListBack;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Send.ListBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;
import com.diucity.dingding.utils.SpUtils;

import java.util.List;

import rx.Observer;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class RecordBinder implements DataBinder<RecordDelegate, NormalBack> {
    @Override
    public void viewBindModel(RecordDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(RecordDelegate viewDelegate, Object object) {
        if (object instanceof ListBean) {
            ListBean bean = (ListBean) object;
            if (bean.getBill_id() == -1) {
            }else if(bean.getBill_id() ==-2){
                bean.setBill_id(-1);
                viewDelegate.isLoading(true);
            }else {
                bean.setBill_id(viewDelegate.getAdapterBillId());
            }
            Network.subscribe(Network.getApi().list(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<ListBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "网络连接错误");
                    viewDelegate.onFinishLoad();
                    viewDelegate.isLoading(false);
                }

                @Override
                public void onNext(ListBack listBack) {
                    viewDelegate.isLoading(false);
                    viewDelegate.onFinishLoad();
                    Log.d("ch", GsonUtils.GsonString(listBack));
                    if (listBack.getCode() == 103) {
                        App.loginOut(viewDelegate.getActivity());
                    }
                    if (listBack.getCode() == 0) {
                        List<ListBack.DataBean.ItemsBean> list = listBack.getData().getItems();
                        Log.d("ch", "size" + list.size());

                        if (list.size() == 0) {
                            viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "没有更多了");
                        } else {
                            if (bean.getBill_id() == -1) {
                                viewDelegate.notifyDataSet(list);
                                SpUtils.putString(viewDelegate.getActivity(), SpUtils.RECORD, GsonUtils.GsonString(listBack));
                            } else
                                viewDelegate.notifyDataAdd(list);
                        }
                    } else {
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, listBack.getMessage());
                    }
                }
            });
        }
    }
}
