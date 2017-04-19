package com.diucity.dingding.delegate;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;


/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class CaptureDelegate extends AppDelegate {

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_capture;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
    }
}
