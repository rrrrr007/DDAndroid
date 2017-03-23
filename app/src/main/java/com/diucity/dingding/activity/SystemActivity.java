package com.diucity.dingding.activity;


import com.diucity.dingding.R;
import com.diucity.dingding.binder.SystemBinder;
import com.diucity.dingding.delegate.SystemDelegate;
import com.diucity.dingding.entity.MessageBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.liaoinstan.springview.widget.SpringView;

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
                String parms2 = GsonUtils.GsonString(new MessageBean(System.currentTimeMillis(), "5f61fb53-4e8d-4d99-85f8-a1e17059edf8"));
                binder.work(viewDelegate, parms2);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        String parms = GsonUtils.GsonString(new MessageBean(System.currentTimeMillis(), "5f61fb53-4e8d-4d99-85f8-a1e17059edf8"));
        binder.work(viewDelegate, parms);
    }
}
