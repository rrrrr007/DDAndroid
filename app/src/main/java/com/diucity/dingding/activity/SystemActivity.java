package com.diucity.dingding.activity;


import com.diucity.dingding.R;
import com.diucity.dingding.app.App;
import com.diucity.dingding.binder.SystemBinder;
import com.diucity.dingding.delegate.SystemDelegate;
import com.diucity.dingding.entity.Send.ListBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.widget.MySpringView;
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
        ((MySpringView) viewDelegate.get(R.id.sv_system)).setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                viewDelegate.onFinishLoad();
            }

            @Override
            public void onLoadmore() {
                binder.work(viewDelegate,new ListBean(App.user.getData().getRecycler_id(),App.user.getData().getAuth_token(),0,10));
                viewDelegate.onFinishLoad();
            }

        });


        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_system_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
    }

    @Override
    public void initData() {
        binder.work(viewDelegate,new ListBean(App.user.getData().getRecycler_id(),App.user.getData().getAuth_token(),0,10));
    }

    @Override
    protected void doAction1() {
        binder.work(viewDelegate,new ListBean(App.user.getData().getRecycler_id(),App.user.getData().getAuth_token(),0,10));
    }
}
