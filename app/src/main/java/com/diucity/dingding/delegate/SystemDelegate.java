package com.diucity.dingding.delegate;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.SystemAdapter;
import com.diucity.dingding.persent.AppDelegate;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class SystemDelegate extends AppDelegate {
    private SystemAdapter adapter;
    private SpringView sv;
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_system;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();

        sv = get(R.id.sv_system);
        sv.setFooter(new DefaultFooter(getActivity()));
        sv.setType(SpringView.Type.FOLLOW);
        sv.setEnable(false);

        adapter = new SystemAdapter(getActivity(),null);
        RecyclerView rv = get(R.id.rv_system);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
    }

    public void notifyData(){
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        adapter.adapterNotify(list);
        if (adapter.getItemCount()>0){
            get(R.id.ll_system_empty).setVisibility(View.GONE);
            sv.setEnable(true);
        } else {
            get(R.id.ll_system_empty).setVisibility(View.VISIBLE);
            sv.setEnable(false);
        }
    }

    public void isLoading(boolean is){
        get(R.id.ll_system_loading).setVisibility(is?View.VISIBLE:View.GONE);
    }

    public void onFinishLoad(){
        sv.onFinishFreshAndLoad();
    }

}
