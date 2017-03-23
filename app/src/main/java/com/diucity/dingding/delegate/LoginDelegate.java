package com.diucity.dingding.delegate;




import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;


/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class LoginDelegate extends AppDelegate {

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public boolean needShow() {
        return true;
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

}
