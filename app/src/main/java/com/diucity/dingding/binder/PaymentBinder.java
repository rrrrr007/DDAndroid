package com.diucity.dingding.binder;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.diucity.dingding.R;
import com.diucity.dingding.activity.PaymentActivity;
import com.diucity.dingding.activity.YWTActivity;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.app.App;
import com.diucity.dingding.delegate.PaymentDelegate;
import com.diucity.dingding.entity.Back.CheckBack;
import com.diucity.dingding.entity.Back.InfoBack;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Back.RequestBack;
import com.diucity.dingding.entity.Send.CheckBean;
import com.diucity.dingding.entity.Send.InfoBean;
import com.diucity.dingding.entity.Send.NotifyBean;
import com.diucity.dingding.entity.Send.RequestBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;

import rx.Observer;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class PaymentBinder implements DataBinder<PaymentDelegate, NormalBack> {
    public boolean is = true;

    @Override
    public void viewBindModel(PaymentDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(PaymentDelegate viewDelegate, Object object) {
        if (object instanceof RequestBean) {
            viewDelegate.showLoadingWarn("请求支付中");
            RequestBean bean = (RequestBean) object;
            Network.subscribe(Network.getApi().request(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<RequestBack>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "网络连接错误");
                    viewDelegate.hideLoadingWarn();
                }

                @Override
                public void onNext(RequestBack o) {
                    if (o.getCode() == 103) {
                        App.loginOut(viewDelegate.getActivity());
                    }
                    if (o.getCode() == 0) {
                        if (bean.getPayment_type() == 1) {
                            work(viewDelegate, new InfoBean(bean.getOrder_id()));
                            ((PaymentActivity) viewDelegate.getActivity()).wxPay(o.getData());
                            viewDelegate.hideLoadingWarn();
                        } else if (bean.getPayment_type() == 2) {
                            Toast.makeText(viewDelegate.getActivity(), "获取订单", Toast.LENGTH_SHORT).show();
                            work(viewDelegate, new InfoBean(bean.getOrder_id()));
                            //PaymentActivity activity = viewDelegate.getActivity();
                            //activity.rollPoll();
                            Intent intent = new Intent(viewDelegate.getActivity(), YWTActivity.class);
                            intent.putExtra("request", o.getData());
                            viewDelegate.hideLoadingWarn();
                            viewDelegate.getActivity().startActivityForResult(intent, 2);
                        } else if (bean.getPayment_type() == 3) {

                        }

                    } else {
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, o.getMessage());
                    }
                    Log.d("ch", GsonUtils.GsonString(o));
                }
            });
        } else if (object instanceof CheckBean) {

            CheckBean bean = (CheckBean) object;
            Network.subscribe(Network.getApi().check(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<CheckBack>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(CheckBack o) {
                    Log.d("ch", GsonUtils.GsonString(o));
                    if (o.getCode() == 103) {
                        App.loginOut(viewDelegate.getActivity());
                    }
                    if (o.getCode() == 0) {
                        if (o.getData().getCheck_code() == 0) {
                            viewDelegate.setVisiable(true, R.id.arl_payment);
                        }
                    }

                }
            });
        } else if (object instanceof InfoBean) {

            InfoBean bean = (InfoBean) object;
            Log.d("ch,", "InfoBean" + GsonUtils.GsonString(bean));
            Network.subscribe(Network.getApi().info(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<InfoBack>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(InfoBack o) {
                    if (o.getCode() == 103) {
                        App.loginOut(viewDelegate.getActivity());
                    }
                    Log.d("ch", GsonUtils.GsonString(o));
                    if (o.getCode() == 0) {
                        viewDelegate.setDialog(o);
                    }
                }
            });
        } else if (object instanceof NotifyBean) {
            viewDelegate.showLoadingWarn("请求支付结果");
            NotifyBean bean = (NotifyBean) object;
            Network.subscribe(Network.getApi().notify(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<NormalBack>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "请求支付结果失败");
                    viewDelegate.hideLoadingWarn();
                }

                @Override
                public void onNext(NormalBack o) {
                    Log.d("ch", GsonUtils.GsonString(o));
                    viewDelegate.hideLoadingWarn();
                    if (o.getCode() == 0) {
                        viewDelegate.showResult(is);
                    } else {
                        viewDelegate.showResult(false);
                    }

                }
            });
        }
    }
}
