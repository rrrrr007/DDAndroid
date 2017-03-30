package com.diucity.dingding.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.BaseAdapter;
import com.diucity.dingding.adapter.ItemAdapter;
import com.diucity.dingding.adapter.ViewPager2Adapter;
import com.diucity.dingding.delegate.PriceDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.widget.AbsListViewScrollEvent;
import com.jakewharton.rxbinding.widget.RxAbsListView;
import com.jakewharton.rxbinding.widget.RxAdapter;
import com.jakewharton.rxbinding.widget.RxAdapterView;

import rx.functions.Action1;

public class PriceActivity extends BaseActivity<PriceDelegate> {
    private int index = 255;
    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected Class<PriceDelegate> getDelegateClass() {
        return PriceDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        ViewPager item = viewDelegate.get(R.id.vp_price_item);
        ViewPager price = viewDelegate.get(R.id.vp_price_price);
        ViewPager2Adapter adapter = (ViewPager2Adapter) item.getAdapter();
        adapter.setListener(position -> price.setCurrentItem(position));
        price.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                adapter.checked(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
