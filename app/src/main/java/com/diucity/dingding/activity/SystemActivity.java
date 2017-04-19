package com.diucity.dingding.activity;


import com.diucity.dingding.R;
import com.diucity.dingding.binder.SystemBinder;
import com.diucity.dingding.delegate.SystemDelegate;
import com.diucity.dingding.entity.MessageBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
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
        RxView.clicks(viewDelegate.get(R.id.iv_system_back)).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.notifyData();
                    //viewDelegate.finish();
                });
    }

    @Override
    public void initData() {
    }
}
