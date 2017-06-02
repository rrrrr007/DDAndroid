package com.diucity.dingding.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

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
        for (int i = 0; i < 3; i++) {
            RecyclerView rv = new RecyclerView(context);
            if (i == 0) {
                rv.setAdapter(new PriceAdapter(context, null));
            } else if (i == 1) {
                rv.setAdapter(new BasketAdapter(context, null));
            } else {
                rv.setAdapter(new AwardAdapter(context, null));
            }
            rv.setLayoutManager(new LinearLayoutManager(context));
            this.model.add(rv);
        }

    }

    public ArrayList<RecyclerView> getModel() {
        return model;
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
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
