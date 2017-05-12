package com.diucity.dingding.delegate;


import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;

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
    }
}
