package com.diucity.dingding.binder;

import android.util.Log;

import com.diucity.dingding.activity.HomeActivity;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.R;
import com.diucity.dingding.api.Network;
import com.diucity.dingding.delegate.PINDelegate;
import com.diucity.dingding.entity.Send.PaysetBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SignUtils;

import rx.Observer;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class PINBinder implements DataBinder<PINDelegate,NormalBack> {
    @Override
    public void viewBindModel(PINDelegate viewDelegate, NormalBack data) {

    }

    @Override
    public void work(PINDelegate viewDelegate, Object object) {
        if (object instanceof PaysetBean){
            PaysetBean bean = (PaysetBean) object;
            viewDelegate.showLoadingWarn("设置支付密码");
            Network.subscribe(Network.getApi().payset(SignUtils.sign(GsonUtils.GsonString(bean)), bean), new Observer<NormalBack>() {

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
                public void onNext(NormalBack back) {
                    viewDelegate.hideLoadingWarn();
                    if (back.getCode()==0) {
                        viewDelegate.startActivity(HomeActivity.class);
                        viewDelegate.finish();
                    } else
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 3, back.getMessage());
                    Log.d("ch", GsonUtils.GsonString(back));
                }
            });
        }
    }
}
