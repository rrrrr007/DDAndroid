package com.diucity.dingding.delegate;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.MissionAdapter;
import com.diucity.dingding.adapter.RecordAdapter;
import com.diucity.dingding.persent.AppDelegate;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class RecordDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_record;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ((TextView) get(R.id.toolbar)).setText("账单明细");
        RecyclerView rv = get(R.id.rv_record);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<String> list = new ArrayList<>();
        list.add("!");
        list.add("!");
        list.add("!");
        rv.setAdapter(new RecordAdapter<>(getActivity(), list));
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(2000);
        rv.setItemAnimator(animator);

    }
}
