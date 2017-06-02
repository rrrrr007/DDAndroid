package com.diucity.dingding.activity;

import android.support.v7.widget.RecyclerView;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.MissionAdapter;
import com.diucity.dingding.delegate.MissionDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MissionActivity extends BaseActivity<MissionDelegate> {

    @Override
    protected Class<MissionDelegate> getDelegateClass() {
        return MissionDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        RecyclerView rv = viewDelegate.get(R.id.rv_mission);
        RxView.clicks(viewDelegate.get(R.id.fl_toolbar)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    ArrayList<String> a = new ArrayList<>();
                    a.add("1");
                    a.add("1");
                    ((MissionAdapter) ((RecyclerView) viewDelegate.get(R.id.rv_mission)).getAdapter()).adapterNotify(a);
                    rv.scrollToPosition(rv.getAdapter().getItemCount() - 1);
                });
    }
}
