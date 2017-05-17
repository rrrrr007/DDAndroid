package com.diucity.dingding.delegate;

import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.R;
import com.diucity.dingding.utils.ClearUtils;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class OptionsDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_options;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        setText(ClearUtils.caculateCacheSize(getActivity()),R.id.tv_options_cache);
    }
}
