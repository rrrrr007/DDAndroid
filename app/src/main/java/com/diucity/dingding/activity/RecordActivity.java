package com.diucity.dingding.activity;


import com.diucity.dingding.delegate.RecordDelegate;
import com.diucity.dingding.persent.DataBinder;

public class RecordActivity extends BaseActivity<RecordDelegate> {


    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected Class<RecordDelegate> getDelegateClass() {
        return RecordDelegate.class;
    }
}
