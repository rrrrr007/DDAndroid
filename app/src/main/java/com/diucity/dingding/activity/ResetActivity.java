package com.diucity.dingding.activity;


import com.diucity.dingding.delegate.ResetDelegate;
import com.diucity.dingding.persent.DataBinder;

public class ResetActivity extends BaseActivity<ResetDelegate> {


    @Override
    protected Class<ResetDelegate> getDelegateClass() {
        return null;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }
}
