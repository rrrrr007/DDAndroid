package com.diucity.dingding.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diucity.dingding.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<String> list;
    private ArrayList<View> model;

    public ViewPagerAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.model = new ArrayList<>();
        this.list = new ArrayList<>();
        if (list != null && list.size() > 0) {
            this.list.addAll(list);
            for (int i = 0; i < this.list.size(); i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.adapter_price, null);
                TextView buy = (TextView) view.findViewById(R.id.adapter_tv_price_buy);
                buy.setText(list.get(i));
                this.model.add(view);
            }
        }
    }

    @Override
    public int getCount() {
        return list.size();
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
        container.removeView((View) object);
    }
}
