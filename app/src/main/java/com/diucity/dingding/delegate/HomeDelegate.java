package com.diucity.dingding.delegate;

import android.support.v4.view.ViewPager;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.HomeViewPager;
import com.diucity.dingding.persent.AppDelegate;


/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class HomeDelegate extends AppDelegate {
    private ViewPager vp;


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public boolean needShow() {
        return true;
    }

    @Override
    public void initWidget() {
        vp = get(R.id.vp_home);
        vp.setAdapter(new HomeViewPager(getActivity()));
    }

}
