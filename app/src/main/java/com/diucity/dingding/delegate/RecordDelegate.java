package com.diucity.dingding.delegate;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.MissionAdapter;
import com.diucity.dingding.adapter.RecordAdapter;
import com.diucity.dingding.persent.AppDelegate;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class RecordDelegate extends AppDelegate {
    private RecordAdapter adapter;
    private SpringView sv;
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
        sv = get(R.id.sv_record);
        sv.setFooter(new DefaultFooter(getActivity()));
        sv.setType(SpringView.Type.FOLLOW);
        sv.setEnable(false);

        RecyclerView rv = get(R.id.rv_record);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecordAdapter(getActivity(),null);
        rv.setAdapter(adapter);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(2000);
        rv.setItemAnimator(animator);
    }

    public void notifyData(){
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        adapter.adapterNotify(list);
        if (adapter.getItemCount()>0){
            get(R.id.ll_record_empty).setVisibility(View.GONE);
            sv.setEnable(true);
        } else {
            get(R.id.ll_record_empty).setVisibility(View.VISIBLE);
            sv.setEnable(false);
        }
    }
    public void isLoading(boolean is){
        get(R.id.ll_record_loading).setVisibility(is?View.VISIBLE:View.GONE);
    }

    public void onFinishLoad(){
        sv.onFinishFreshAndLoad();
    }
}
