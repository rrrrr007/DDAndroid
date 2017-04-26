package com.diucity.dingding.delegate;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.AwardAdapter;
import com.diucity.dingding.adapter.HomeViewPager;
import com.diucity.dingding.adapter.PriceAdapter;
import com.diucity.dingding.entity.Back.TaskBack;
import com.diucity.dingding.persent.AppDelegate;

import java.util.List;


/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class HomeDelegate extends AppDelegate {
    private ViewPager vp;
    private HomeViewPager adapter;


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
        adapter = new HomeViewPager(getActivity());
        vp.setAdapter(adapter);
    }


    public void getInsideAdapterNotify(int postion,Object o) {
        switch (postion){
            case 0:
                PriceAdapter adapter0 = (PriceAdapter) this.adapter.getModel().get(0).getAdapter();
                adapter0.updateBySet((List<String>) o);
                break;
            case 1:
                PriceAdapter adapter1 = (PriceAdapter) this.adapter.getModel().get(1).getAdapter();
                adapter1.updateBySet((List<String>) o);
                break;
            case 2:
                AwardAdapter adapter2 = (AwardAdapter) this.adapter.getModel().get(2).getAdapter();
                adapter2.updateBySet((List<TaskBack.DataBean.TasksBean>) o);
                break;
        }
    }
}
