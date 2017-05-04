package com.diucity.dingding.binder;

import android.util.Log;
import android.view.View;

import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.delegate.Forget2Delegate;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Send.ResetBean;
import com.diucity.dingding.entity.Send.SmsBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;

import rx.Observer;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class Forget2Binder implements DataBinder<Forget2Delegate, NormalBack> {
    @Override
    public void viewBindModel(Forget2Delegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(Forget2Delegate viewDelegate, Object object) {
        if (object instanceof SmsBean) {
            viewDelegate.showLoadingWarn("短信发送中");
            SmsBean bean = (SmsBean) object;
            Network.subscribe(Network.getApi().sms(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<NormalBack>() {
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
                public void onNext(NormalBack normalBack) {
                    Log.d("ch", GsonUtils.GsonString(normalBack));
                    viewDelegate.hideLoadingWarn();
                    if (normalBack.getCode()==0){
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 1, "重发成功");
                    }else {
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 3, normalBack.getMessage());
                    }
                }
            });
        }else if (object instanceof ResetBean){
            viewDelegate.showLoadingWarn("密码重设中");
            ResetBean bean = (ResetBean) object;
            Network.subscribe(Network.getApi().reset(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<NormalBack>() {

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
                public void onNext(NormalBack normalBack) {
                    Log.d("ch", GsonUtils.GsonString(normalBack));
                    viewDelegate.hideLoadingWarn();
                    if (normalBack.getCode()==0){
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 1, "重置成功");
                        viewDelegate.setText("返回",R.id.btn_forget2_finish);
                        viewDelegate.setOnClickListener(v -> {
                             viewDelegate.finish();
                        },R.id.btn_forget2_finish);
                    }else {
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 3, normalBack.getMessage());
                    }
                }
            });
        }
    }
}