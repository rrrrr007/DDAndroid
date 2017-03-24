package com.diucity.dingding.delegate;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class StatisticsDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_statistics;
    }

    @Override
    public boolean needShow() {
        return false;
    }
}
