package com.diucity.dingding.binder;

import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.app.App;
import com.diucity.dingding.delegate.SystemDelegate;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Back.SystemBack;
import com.diucity.dingding.entity.Send.ListBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;
import com.diucity.dingding.utils.SpUtils;
import com.diucity.dingding.widget.MyFooter;
import com.liaoinstan.springview.widget.SpringView;

import java.util.List;

import rx.Observer;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class SystemBinder implements DataBinder<SystemDelegate, NormalBack> {

    @Override
    public void viewBindModel(SystemDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(SystemDelegate viewDelegate, Object object) {
        if (object instanceof ListBean) {
            ListBean bean = (ListBean) object;
            if (bean.getNotice_id() == -1) {
            } else if (bean.getNotice_id() == -2) {
                bean.setNotice_id(-1);
                viewDelegate.isLoading(true);
            } else {
                bean.setNotice_id(viewDelegate.getNoticeId());
            }
            Log.d("ch", "我的请求" + GsonUtils.GsonString(bean));
            Network.subscribe(Network.getApi().notices(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<SystemBack>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "网络连接错误");
                    viewDelegate.onFinishLoad();
                    viewDelegate.isLoading(false);
                }

                @Override
                public void onNext(SystemBack s) {
                    viewDelegate.isLoading(false);
                    viewDelegate.onFinishLoad();
                    Log.d("ch", GsonUtils.GsonString(s));
                    if (s.getCode() == 103) {
                        App.loginOut(viewDelegate.getActivity());
                    }else if (s.getCode() == 0) {
                        List<SystemBack.DataBean.NoticesBean> notices = s.getData().getNotices();
                        if (notices.size() == 0) {
                            viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "没有更多了");
                            return;
                        } else {
                            if (bean.getNotice_id() == -1) {
                                SpUtils.putString(viewDelegate.getActivity(), SpUtils.SYSTEM, GsonUtils.GsonString(s));
                                viewDelegate.notifyDataSet(notices);
                            } else {
                                viewDelegate.notifyDataAdd(notices);
                            }
                        }
                    } else {
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, s.getMessage());
                    }


                }
            });
        }
    }
}
