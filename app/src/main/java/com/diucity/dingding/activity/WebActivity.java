package com.diucity.dingding.activity;

import com.diucity.dingding.delegate.WebDelegate;
import com.diucity.dingding.persent.DataBinder;

public class WebActivity extends BaseActivity<WebDelegate> {

    @Override
    protected Class<WebDelegate> getDelegateClass() {
        return WebDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }


}
