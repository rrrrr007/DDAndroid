package com.diucity.dingding.activity;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.DetailDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

public class DetailActivity extends BaseActivity<DetailDelegate> {


    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected Class<DetailDelegate> getDelegateClass() {
        return DetailDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_detail_back)).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.finish();
                });
    }

    @Override
    protected void onDestroy() {
        viewDelegate.setNull();
        super.onDestroy();
    }
}
