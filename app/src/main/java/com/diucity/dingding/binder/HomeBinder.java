package com.diucity.dingding.binder;

import android.util.Log;

import com.diucity.dingding.entity.Back.BasketBack;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Back.ScrapsBack;
import com.diucity.dingding.entity.Back.TaskBack;
import com.diucity.dingding.entity.Back.TodayBack;
import com.diucity.dingding.entity.Send.BasketBean;
import com.diucity.dingding.entity.Send.ScrapsBean;
import com.diucity.dingding.entity.Send.TaskBean;
import com.diucity.dingding.entity.Send.TodayBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;
import com.diucity.dingding.utils.SpUtils;
import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.delegate.HomeDelegate;

import rx.Observer;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class HomeBinder implements DataBinder<HomeDelegate, NormalBack> {
    @Override
    public void viewBindModel(HomeDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(HomeDelegate viewDelegate, Object object) {
        viewDelegate.setText("更新中...",R.id.tv_home_title);
        viewDelegate.setVisiable(true,R.id.progress_home);
        if (object instanceof ScrapsBean){
            ScrapsBean bean = (ScrapsBean) object;
            Network.subscribe(Network.getApi().scraps(SignUtils.sign(GsonUtils.GsonString(bean)),bean), new Observer<ScrapsBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(ScrapsBack s) {
                    Log.d("ch",GsonUtils.GsonString(s));

                }
            });
        }else if (object instanceof TodayBean){
            TodayBean bean = (TodayBean) object;
            Network.subscribe(Network.getApi().today(SignUtils.sign(GsonUtils.GsonString(bean)),bean), new Observer<TodayBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "网络连接出错");
                    viewDelegate.setText("叮叮回收",R.id.tv_home_title);
                    viewDelegate.setVisiable(false,R.id.progress_home);
                    e.printStackTrace();
                }

                @Override
                public void onNext(TodayBack s) {
                    /*if (s.getCode()==0){
                        SpUtils.putLong(viewDelegate.getActivity(),SpUtils.UPDATE,System.currentTimeMillis());
                    }*/
                    if (s.getCode()==0){
                        SpUtils.putString(viewDelegate.getActivity(),SpUtils.TODAY,GsonUtils.GsonString(s));
                        viewDelegate.getInsideAdapterNotify(0,s.getData().getScraps());
                        work(viewDelegate,new ScrapsBean(bean.getRecycler_id()));
                        work(viewDelegate,new TaskBean(bean.getRecycler_id()));
                        work(viewDelegate,new BasketBean(bean.getRecycler_id(), "sjsj2010A20suycxx"));
                    }
                    Log.d("ch",GsonUtils.GsonString(s));
                    viewDelegate.setText("叮叮回收",R.id.tv_home_title);
                    viewDelegate.setVisiable(false,R.id.progress_home);
                }
            });
        }else if (object instanceof TaskBean){
            TaskBean bean = (TaskBean) object;
            Network.subscribe(Network.getApi().task(SignUtils.sign(GsonUtils.GsonString(bean)),bean), new Observer<TaskBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(TaskBack s) {
                    Log.d("ch",GsonUtils.GsonString(s));
                    if (s.getCode()==0){
                        viewDelegate.getInsideAdapterNotify(2,s.getData().getTasks());
                    }
                }
            });
        }else if (object instanceof BasketBean){
            BasketBean bean = (BasketBean) object;
            Network.subscribe(Network.getApi().basket(SignUtils.sign(GsonUtils.GsonString(bean)),bean), new Observer<BasketBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(BasketBack s) {
                    if (s.getCode()==0){
                        viewDelegate.getInsideAdapterNotify(1,s.getData().getData());
                    }
                    Log.d("ch",GsonUtils.GsonString(s));
                }
            });
        }
    }
}
