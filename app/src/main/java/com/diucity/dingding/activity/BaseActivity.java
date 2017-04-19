package com.diucity.dingding.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.diucity.dingding.app.App;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.persent.DataBindActivity;
import com.diucity.dingding.persent.IDelegate;
import com.diucity.dingding.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public abstract class BaseActivity<T extends IDelegate> extends DataBindActivity<T> {
    protected final String TAG = getClass().getSimpleName();
    public BaseActivity activity;
    protected List<Subscription> subscriptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        subscriptions = new ArrayList<>();
        super.onCreate(savedInstanceState);
        activity = this;
        ((App) ActivityUtils.getContext()).addActivity(this);

    }
    public void isShowSmallWarn(boolean is){
        if (is){
            //show
            ((AppDelegate)viewDelegate).showSmallWarn();
        }else {
            //noshow
            ((AppDelegate)viewDelegate).hideSmallWarn();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }



    @Override
    protected void onDestroy() {
        ((App) ActivityUtils.getContext()).removeActivity(this);
        for (Subscription subscription : subscriptions) {
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
        subscriptions=null;
        super.onDestroy();
    }


}
