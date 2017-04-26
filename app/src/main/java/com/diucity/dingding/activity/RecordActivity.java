package com.diucity.dingding.activity;


import com.diucity.dingding.R;
import com.diucity.dingding.delegate.RecordDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;
import com.liaoinstan.springview.widget.SpringView;

import java.util.concurrent.TimeUnit;

public class RecordActivity extends BaseActivity<RecordDelegate> {


    @Override
    public DataBinder getDataBinder() {
        return null;
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
            }

            @Override
            public void onLoadmore() {
                viewDelegate.onFinishLoad();
            }
        });

        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_record_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.notifyData();
                    //finish();
                });

    }
}
