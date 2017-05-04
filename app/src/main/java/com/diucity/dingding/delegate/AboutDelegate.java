package com.diucity.dingding.delegate;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.ActivityUtils;
import com.diucity.dingding.utils.ShadowProperty;
import com.diucity.dingding.utils.ShadowViewDrawable;

/**
 * Created by Administrator on 2017/4/20 0020.
 */
public class AboutDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        ShadowProperty sp = new ShadowProperty()
                .setShadowColor(0x1A000000)
                .setShadowDy(ActivityUtils.dip2px(getActivity(),1))
                .setShadowRadius(ActivityUtils.dip2px(getActivity(),2))
                .setShadowSide(ShadowProperty.ALL);
        ShadowViewDrawable sd = new ShadowViewDrawable(sp, ContextCompat.getColor(getActivity(),R.color.selector_white),ActivityUtils.dip2px(getActivity(),5) , ActivityUtils.dip2px(getActivity(),5));
        ViewCompat.setBackground(get(R.id.ll_about_card), sd);
        ViewCompat.setLayerType(get(R.id.ll_about_card), ViewCompat.LAYER_TYPE_SOFTWARE, null);
    }
}
