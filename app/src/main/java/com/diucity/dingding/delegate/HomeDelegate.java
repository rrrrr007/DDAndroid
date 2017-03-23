package com.diucity.dingding.delegate;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.HomeAdapter;
import com.diucity.dingding.persent.AppDelegate;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class HomeDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        RecyclerView rv = get(R.id.rv_home);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<String> model = new ArrayList<>();
        model.add("1_______");
        model.add("2_______");
        model.add("3_______");
        model.add("4_______");
        model.add("5_______");
        model.add("6_______");
        rv.setAdapter(new HomeAdapter(getActivity(),model));

    }


}
