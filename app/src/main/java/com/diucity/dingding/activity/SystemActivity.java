package com.diucity.dingding.activity;


import com.diucity.dingding.R;
import com.diucity.dingding.binder.SystemBinder;
import com.diucity.dingding.delegate.SystemDelegate;
import com.diucity.dingding.entity.Send.ListBean;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;
import com.liaoinstan.springview.widget.SpringView;

import java.util.concurrent.TimeUnit;

public class SystemActivity extends BaseActivity<SystemDelegate> {


    @Override
    public DataBinder getDataBinder() {
        return new SystemBinder();
    }

    @Override
    protected Class getDelegateClass() {
        return SystemDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        ((SpringView) viewDelegate.get(R.id.sv_system)).setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
            }

            @Override
            public void onLoadmore() {
                viewDelegate.onFinishLoad();
            }
        });
        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_system_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.notifyData();
                    //viewDelegate.finish();
                });
    }

    @Override
    public void initData() {
        binder.work(viewDelegate,new ListBean("1001","993jal-2lakd2sj",1,10));
    }
}
