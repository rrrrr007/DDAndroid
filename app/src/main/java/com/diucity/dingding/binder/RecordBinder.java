package com.diucity.dingding.binder;

import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.delegate.RecordDelegate;
import com.diucity.dingding.entity.Back.ListBack;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Send.ListBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;

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
            Network.subscribe(Network.getApi().list(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<ListBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "网络连接错误");
                }

                @Override
                public void onNext(ListBack listBack) {
                    Log.d("ch", GsonUtils.GsonString(listBack));
                    if (listBack.getCode() == 0) {
                        List<ListBack.DataBean.ItemsBean> list = listBack.getData().getItems();
                        viewDelegate.notifyData(list);
                    } else {
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, listBack.getMessage());
                    }
                }
            });
        }
    }
}
