package com.diucity.dingding.activity;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.diucity.dingding.R;
import com.diucity.dingding.app.App;
import com.diucity.dingding.binder.PaymentBinder;
import com.diucity.dingding.delegate.PaymentDelegate;
import com.diucity.dingding.entity.Back.WXBack;
import com.diucity.dingding.entity.Send.CheckBean;
import com.diucity.dingding.entity.Send.RequestBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class PaymentActivity extends BaseActivity<PaymentDelegate> {
    private final int WXPAY = 101;
    private final int YWTPAY = 102;
    private final int ZFBPAY = 103;
    private int choice = WXPAY;
    private static final String APP_ID = "wx3ea5c30a2e13c752";
    private Subscription subscribe;

    private IWXAPI api;


    @Override
    public DataBinder getDataBinder() {
        return new PaymentBinder();
    }

    @Override
    protected Class<PaymentDelegate> getDelegateClass() {
        return PaymentDelegate.class;
    }

    @Override
    protected void bindEvenListener() {

        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_payment_back)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid ->
                viewDelegate.finish()
        );

        RxView.clicks(viewDelegate.get(R.id.tv_payment_back)).subscribe(aVoid -> {
            App.getAcitvity("activity.CountActivity").finish();
            viewDelegate.finish();
        });
        //支付选择
        RxView.clicks(viewDelegate.get(R.id.all_payment_wx)).subscribe(aVoid -> {
            choice = WXPAY;
            setPayIcon();
        });
        RxView.clicks(viewDelegate.get(R.id.all_payment_ywt)).subscribe(aVoid -> {
            choice = YWTPAY;
            setPayIcon();
        });
        RxView.clicks(viewDelegate.get(R.id.all_payment_zfb)).subscribe(aVoid -> {
            choice = ZFBPAY;
            setPayIcon();
        });
        //展开详情
        RxView.clicks(viewDelegate.get(R.id.tv_payment_detail)).subscribe(aVoid -> {
            setImageRight(viewDelegate.get(R.id.rv_payment).getVisibility() == View.GONE);
            viewDelegate.setVisiable(viewDelegate.get(R.id.rv_payment).getVisibility() == View.GONE, R.id.rv_payment);
            viewDelegate.setVisiable(viewDelegate.get(R.id.view_payment_line).getVisibility() == View.GONE, R.id.view_payment_line);
        });
        RxView.clicks(viewDelegate.get(R.id.btn_payment_pay)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
            if (choice == WXPAY) {
                int id = getIntent().getIntExtra("payId", -255);
                if (id == -255) {
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 3, "订单生成失败");
                    return;
                } else {
                    binder.work(viewDelegate, new RequestBean(id, 1, "192.168.3.11"));
                }
            } else if (choice == YWTPAY) {
                int id = getIntent().getIntExtra("payId", -255);
                if (id == -255) {
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 3, "订单生成失败");
                } else {
                    binder.work(viewDelegate, new RequestBean(id, 2, " "));
                }

            } else {
                Toast.makeText(activity, "尽请期待", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    public void wxPay(String str) {
        api = WXAPIFactory.createWXAPI(this, APP_ID, false);
        if (!api.isWXAppInstalled()) {
            Toast.makeText(this, "未安装微信", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean b = api.registerApp(APP_ID);
        Toast.makeText(this, "注册到微信" + b, Toast.LENGTH_SHORT).show();
        WXBack bean = GsonUtils.GsonToBean(str, WXBack.class);
        Log.d("ch", "解析参数" + GsonUtils.GsonString(bean));
        PayReq pay = new PayReq();
        pay.appId = bean.getAppid();
        Log.d("ch", "getAppid" + bean.getAppid());
        pay.partnerId = bean.getPartnerid();
        Log.d("ch", "partnerId" + bean.getPartnerid());
        pay.prepayId = bean.getPrepayid();
        Log.d("ch", "prepayId" + bean.getPrepayid());
        pay.packageValue = bean.getPackageX();
        Log.d("ch", "packageValue" + bean.getPackageX());
        pay.nonceStr = bean.getNoncestr();
        Log.d("ch", "nonceStr" + bean.getNoncestr());
        pay.timeStamp = bean.getTimestamp();
        Log.d("ch", "timeStamp" + bean.getTimestamp());
        pay.sign = bean.getSign();
        Log.d("ch", "sign" + bean.getSign());
        api.sendReq(pay);
    }

    public void rollPoll() {
        if (subscribe != null) return;
        subscribe = Observable.interval(5, 5, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.d("ch", "along" + aLong);
                if (viewDelegate.get(R.id.arl_payment).getVisibility() == View.VISIBLE) {
                    return;
                }
                orderCheck();
            }
        });
        subscriptions.add(subscribe);
    }

    private void orderCheck() {
        binder.work(viewDelegate, new CheckBean(App.user.getData().getRecycler_id(), App.user.getData().getAuth_token(), getIntent().getIntExtra("orderId", 0)));
    }

    private void setPayIcon() {
        switch (choice) {
            case 101:
                viewDelegate.setEnable(false, R.id.iv_payment_wx);
                viewDelegate.setEnable(true, R.id.iv_payment_ywt);
                viewDelegate.setEnable(true, R.id.iv_payment_zfb);
                break;
            case 102:
                viewDelegate.setEnable(false, R.id.iv_payment_ywt);
                viewDelegate.setEnable(true, R.id.iv_payment_wx);
                viewDelegate.setEnable(true, R.id.iv_payment_zfb);
                break;
            case 103:
                viewDelegate.setEnable(false, R.id.iv_payment_zfb);
                viewDelegate.setEnable(true, R.id.iv_payment_wx);
                viewDelegate.setEnable(true, R.id.iv_payment_ywt);
                break;
        }
    }

    public void setImageRight(boolean open) {
        Drawable img;
        if (open) {
            img = ContextCompat.getDrawable(this, R.mipmap.ic_buy_arrows_list_up);
        } else {
            img = ContextCompat.getDrawable(this, R.mipmap.ic_buy_arrows_list);
        }

        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        ((TextView) viewDelegate.get(R.id.tv_payment_detail)).setCompoundDrawables(null, null, img, null);
    }

    public void showSuccess() {
        viewDelegate.setVisiable(true, R.id.arl_payment);
        viewDelegate.setVisiable(false, R.id.btn_payment_pay);
    }

    public void showFailure() {
        viewDelegate.setText(viewDelegate.getText(R.id.btn_payment_pay).replace("确认", "重新"), R.id.btn_payment_pay);
        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 3, "支付失败");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 2:
                boolean ok = data.getBooleanExtra("ok", false);
                if (ok) {
                    showSuccess();
                } else {
                    showFailure();
                }
                break;
            default:
                break;
        }
    }
}
