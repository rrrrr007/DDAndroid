package com.diucity.dingding.persent;

import android.os.Bundle;
import android.view.View;

//集成数据-视图绑定的Fragment基类(Presenter层)
public abstract class DataBindFragment<T extends IDelegate> extends FragmentPresenter<T> {

    protected DataBinder binder;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binder = getDataBinder();
    }

    public abstract DataBinder getDataBinder();

    public <D> void notifyModelChanged(D data) {
        if (binder != null)
            binder.viewBindModel(viewDelegate, data);
    }
}
