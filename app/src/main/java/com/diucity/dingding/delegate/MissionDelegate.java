package com.diucity.dingding.delegate;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.MissionAdapter;
import com.diucity.dingding.persent.AppDelegate;


/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class MissionDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_mission;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ((TextView) get(R.id.toolbar)).setText("任务列表");
        RecyclerView rv = get(R.id.rv_mission);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new MissionAdapter(getActivity(), null));
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(2000);
        rv.setItemAnimator(animator);
    }
}
