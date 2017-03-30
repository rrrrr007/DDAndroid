package com.diucity.dingding.delegate;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.diucity.dingding.R;

import com.diucity.dingding.adapter.ViewPager2Adapter;
import com.diucity.dingding.adapter.ViewPagerAdapter;
import com.diucity.dingding.persent.AppDelegate;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class PriceDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_price;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ((TextView) get(R.id.toolbar)).setText("今日价格");
        ViewPager item = get(R.id.vp_price_item);
        ArrayList<String> list = new ArrayList<>();
        list.add("废纸");
        list.add("金属");
        list.add("熟料");
        list.add("钢材");
        list.add("玻璃");
        list.add("尽请期待");
        item.setAdapter(new ViewPager2Adapter(getActivity(), list));
        item.setOffscreenPageLimit(list.size());
        item.setPageMargin(-750);
        ViewPager price = get(R.id.vp_price_price);
        price.setAdapter(new ViewPagerAdapter(getActivity(),list));
    }
}
