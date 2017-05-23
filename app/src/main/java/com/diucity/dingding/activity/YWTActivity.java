package com.diucity.dingding.activity;


import com.diucity.dingding.delegate.YWTDelegate;
import com.diucity.dingding.persent.DataBinder;

public class YWTActivity extends BaseActivity<YWTDelegate> {

    @Override
    protected Class<YWTDelegate> getDelegateClass() {
        return YWTDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }
}
