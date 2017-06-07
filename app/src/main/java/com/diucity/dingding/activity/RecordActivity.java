package com.diucity.dingding.activity;



import com.diucity.dingding.R;
import com.diucity.dingding.app.App;
import com.diucity.dingding.binder.RecordBinder;
import com.diucity.dingding.delegate.RecordDelegate;
import com.diucity.dingding.entity.Send.ListBean;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;
import com.liaoinstan.springview.widget.SpringView;

import java.util.concurrent.TimeUnit;

public class RecordActivity extends BaseActivity<RecordDelegate> {
    @Override
    public DataBinder getDataBinder() {
        return new RecordBinder();
    }

    @Override
    protected Class<RecordDelegate> getDelegateClass() {
        return RecordDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        //下拉刷新
        ((SpringView) viewDelegate.get(R.id.sv_record)).setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                binder.work(viewDelegate, new ListBean(App.user.getData().getRecycler_id(), App.user.getData().getAuth_token(), -1, 10));
            }

            @Override
            public void onLoadmore() {
                binder.work(viewDelegate, new ListBean(App.user.getData().getRecycler_id(), App.user.getData().getAuth_token(), 1, 10));
            }
        });

        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_record_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
    }
}
