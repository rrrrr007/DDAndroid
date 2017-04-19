package com.diucity.dingding.persent;

import android.os.Bundle;

import rx.Subscription;


//集成数据-视图绑定的Activity基类(Presenter层)
public abstract class DataBindActivity<T extends IDelegate> extends
        ActivityPresenter<T> {
    protected DataBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = getDataBinder();
        initData();
        bindEvenListener();

    }

    protected void bindEvenListener() {
    }

    public void initData() {
    }

    public abstract DataBinder getDataBinder();

    public <D extends IModel> void notifyModelChanged(D data){
        if (binder != null){
            binder.viewBindModel(viewDelegate,data);
        }
    }
}