package com.diucity.dingding.delegate;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.diucity.dingding.R;
import com.diucity.dingding.activity.ResetActivity;
import com.diucity.dingding.adapter.AwardAdapter;
import com.diucity.dingding.adapter.BasketAdapter;
import com.diucity.dingding.adapter.HomeViewPager;
import com.diucity.dingding.adapter.PriceAdapter;
import com.diucity.dingding.entity.Back.BasketBack;
import com.diucity.dingding.entity.Back.ScrapsBack;
import com.diucity.dingding.entity.Back.TaskBack;
import com.diucity.dingding.entity.Back.TodayBack;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.widget.SwichBarView;

import java.util.List;


/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class HomeDelegate extends AppDelegate {
    private ViewPager vp;
    private HomeViewPager adapter;
    private LayoutTransition transition;

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
        SwichBarView sbv = get(R.id.sbv_home);
        sbv.connectViewPager(vp);
    }


    public void getInsideAdapterNotify(int postion, Object o) {
        switch (postion) {
            case 0:
                PriceAdapter adapter0 = (PriceAdapter) this.adapter.getModel().get(0).getAdapter();
                adapter0.updateBySet((List<TodayBack.DataBean.ScrapsBean>) o);
                break;
            case 1:
                BasketAdapter adapter1 = (BasketAdapter) this.adapter.getModel().get(1).getAdapter();
                adapter1.updateBySet((List<ScrapsBack.Data.Scraps>) o);
                break;
            case 2:
                AwardAdapter adapter2 = (AwardAdapter) this.adapter.getModel().get(2).getAdapter();
                adapter2.updateBySet((List<TaskBack.DataBean.TasksBean>) o);
                break;
        }
    }

    public void setBasket(BasketBack back) {
        BasketAdapter adapter = (BasketAdapter) this.adapter.getModel().get(1).getAdapter();
        adapter.setBasket(back);
    }

    public void showStatus(ViewGroup vg) {
        if (transition == null) {
            setupAnimations(vg.getHeight());
            vg.setLayoutTransition(transition);
        }
        if (vg.getChildCount() > 0) {
            return;
        }
        vg.getHeight();
        View view = getInflater().inflate(R.layout.view_state, null);
        view.findViewById(R.id.tv_view_forget).setOnClickListener(v -> {
            startAcitityWithAnim(ResetActivity.class);
            if (vg.getChildCount() > 0)
                vg.removeView(view);
        });
        view.findViewById(R.id.iv_view_src).setOnClickListener(v -> {
            if (vg.getChildCount() > 0)
                vg.removeView(view);
        });

        vg.addView(view);
    }

    private void setupAnimations(float height) {
        transition = new LayoutTransition();
        // Adding
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "translationY", -height,
                0).setDuration(
                transition.getDuration(LayoutTransition.APPEARING));
        transition.setAnimator(LayoutTransition.APPEARING, animIn);
        // Removing
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "translationY", 0,
                -height).setDuration(
                transition.getDuration(LayoutTransition.DISAPPEARING));
        transition.setAnimator(LayoutTransition.DISAPPEARING, animOut);
    }

}
