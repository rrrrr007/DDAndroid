package com.diucity.dingding.binder;

import android.util.Log;

import com.diucity.dingding.activity.HomeActivity;
import com.diucity.dingding.activity.PINActivity;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.app.App;
import com.diucity.dingding.delegate.LoginDelegate;
import com.diucity.dingding.entity.Back.LoginBack;
import com.diucity.dingding.entity.Back.TodayBack;
import com.diucity.dingding.entity.Send.LoginBean;
import com.diucity.dingding.entity.Send.TodayBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;
import com.diucity.dingding.R;
import com.diucity.dingding.utils.SpUtils;

import rx.Observer;


/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class LoginBinder implements DataBinder<LoginDelegate, LoginBack> {
    @Override
    public void viewBindModel(LoginDelegate viewDelegate, LoginBack data) {

    }

    @Override
    public void work(LoginDelegate viewDelegate, Object object) {
        if (object instanceof LoginBean) {
            LoginBean bean = (LoginBean) object;
            Log.d("ch", GsonUtils.GsonString(bean));
            viewDelegate.showLoadingWarn("正在登录中");
            Network.subscribe(Network.getApi().login(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<LoginBack>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    viewDelegate.hideLoadingWarn();
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "网络连接出错");
                }

                @Override
                public void onNext(LoginBack back) {
                    viewDelegate.hideLoadingWarn();
                    if (back.getCode() == 0) {
                        SpUtils.putString(viewDelegate.getActivity(), SpUtils.USER, GsonUtils.GsonString(back));
                        App.user = back;
                        viewDelegate.startActivity(HomeActivity.class);
                        work(viewDelegate,new TodayBean(back.getData().getRecycler_id(),0,0));

                    } else
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 3, back.getMessage());
                    Log.d("ch", GsonUtils.GsonString(back));
                }
            });
        }else if (object instanceof TodayBean){
            TodayBean bean = (TodayBean) object;
            Network.subscribe(Network.getApi().today(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<TodayBack>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(TodayBack o) {
                    if (o.getCode()==0){
                        SpUtils.putString(viewDelegate.getActivity(),SpUtils.TODAY,GsonUtils.GsonString(o));
                        Log.d("ch","存了");
                    }
                }
            });
        }
    }
}
