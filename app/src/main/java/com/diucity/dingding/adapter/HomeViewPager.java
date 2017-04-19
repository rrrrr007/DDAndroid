package com.diucity.dingding.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diucity.dingding.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class HomeViewPager extends PagerAdapter {
    private ArrayList<RecyclerView> model;
    private Context context;

    public HomeViewPager(Context context) {
        this.context = context;
        this.model = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("!");
        list.add("!");
        list.add("!");
        for (int i =0;i<3;i++){
            RecyclerView rv = new RecyclerView(context);
            if (i==2){
                list.add("a");
                rv.setAdapter(new AwardAdapter(context,list));
            }else {
                rv.setAdapter(new PriceAdapter(context,list));
            }
            rv.setLayoutManager(new LinearLayoutManager(context));
            this.model.add(rv);
        }

    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(model.get(position));
        return model.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(model.get(position));
    }
}
