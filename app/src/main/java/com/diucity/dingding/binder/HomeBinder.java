package com.diucity.dingding.binder;

import android.text.TextUtils;
import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.app.App;
import com.diucity.dingding.delegate.HomeDelegate;
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
        if (object instanceof ScrapsBean) {
            viewDelegate.setText("更新中...", R.id.tv_home_title);
            viewDelegate.setVisiable(true, R.id.progress_home);
            ScrapsBean bean = (ScrapsBean) object;
            Network.subscribe(Network.getApi().scraps(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<ScrapsBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "网络连接出错");
                    viewDelegate.setText("叮叮回收", R.id.tv_home_title);
                    viewDelegate.setVisiable(false, R.id.progress_home);
                    e.printStackTrace();
                    readCache(viewDelegate);


                }

                @Override
                public void onNext(ScrapsBack s) {
                    if (s.getCode() == 0) {
                        if (s.getData().getScraps().size() > 0) {
                            viewDelegate.getInsideAdapterNotify(1, s.getData().getScraps());
                            SpUtils.putString(viewDelegate.getActivity(), SpUtils.SCRAPS, GsonUtils.GsonString(s));
                            work(viewDelegate, new TodayBean(bean.getRecycler_id(), App.longitude, App.latitude));
                        }
                        work(viewDelegate, new TaskBean(bean.getRecycler_id()));
                    } else {
                        readCache(viewDelegate);
                    }
                    Log.d("ch", GsonUtils.GsonString(s));
                    viewDelegate.setText("叮叮回收", R.id.tv_home_title);
                    viewDelegate.setVisiable(false, R.id.progress_home);


                }
            });
        } else if (object instanceof TodayBean) {
            TodayBean bean = (TodayBean) object;
            Log.d("ch", "long" + bean.getLongitude() + "latitude" + bean.getLatitude());
            Network.subscribe(Network.getApi().today(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<TodayBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();

                }

                @Override
                public void onNext(TodayBack s) {
                    if (s.getCode() == 0) {
                        viewDelegate.getInsideAdapterNotify(0, s.getData().getScraps());
                        SpUtils.putString(viewDelegate.getActivity(), SpUtils.TODAY, GsonUtils.GsonString(s));
                        work(viewDelegate, new BasketBean(bean.getRecycler_id(), App.user.getData().getAuth_token()));
                    }
                    Log.d("ch", GsonUtils.GsonString(s));
                }
            });
        } else if (object instanceof TaskBean) {
            TaskBean bean = (TaskBean) object;
            Network.subscribe(Network.getApi().task(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<TaskBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(TaskBack s) {
                    Log.d("ch", GsonUtils.GsonString(s));
                    if (s.getCode() == 0) {
                        viewDelegate.getInsideAdapterNotify(2, s.getData().getTasks());
                        SpUtils.putString(viewDelegate.getActivity(), SpUtils.TASK, GsonUtils.GsonString(s));
                    }
                }
            });
        } else if (object instanceof BasketBean) {
            BasketBean bean = (BasketBean) object;
            Log.d("ch", "" + GsonUtils.GsonString(bean));
            Network.subscribe(Network.getApi().basket(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<BasketBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(BasketBack s) {
                    if (s.getCode() == 103) {
                        App.loginOut(viewDelegate.getActivity());
                    }
                    if (s.getCode() == 0) {
                        viewDelegate.setBasket(s);
                    }

                    Log.d("ch", "baseket" + GsonUtils.GsonString(s));
                }
            });
        }
    }

    private void readCache(HomeDelegate a) {
        String s = SpUtils.getString(a.getActivity(), SpUtils.SCRAPS);
        if (!TextUtils.isEmpty(s)) {
            a.getInsideAdapterNotify(1, GsonUtils.GsonToBean(s, ScrapsBack.class).getData().getScraps());
        }
        String t = SpUtils.getString(a.getActivity(), SpUtils.TODAY);
        if (!TextUtils.isEmpty(t)) {
            a.getInsideAdapterNotify(0, GsonUtils.GsonToBean(t, TodayBack.class).getData().getScraps());
        }
        String k = SpUtils.getString(a.getActivity(), SpUtils.TASK);
        if (!TextUtils.isEmpty(k)) {
            a.getInsideAdapterNotify(2, GsonUtils.GsonToBean(k, TaskBack.class).getData().getTasks());
        }
    }
}
