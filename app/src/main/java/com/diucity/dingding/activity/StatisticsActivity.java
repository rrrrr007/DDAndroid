package com.diucity.dingding.activity;


import com.diucity.dingding.delegate.StatisticsDelegate;
import com.diucity.dingding.persent.DataBinder;

public class StatisticsActivity extends BaseActivity<StatisticsDelegate> {

    @Override
    protected Class<StatisticsDelegate> getDelegateClass() {
        return StatisticsDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }


}
