package com.diucity.dingding.activity;


import android.util.Log;
import android.widget.Toast;

import com.diucity.dingding.app.App;
import com.diucity.dingding.binder.PaymentBinder;
import com.diucity.dingding.entity.Back.WXBack;
import com.diucity.dingding.entity.Send.RequestBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.R;
import com.diucity.dingding.delegate.PaymentDelegate;
import com.diucity.dingding.utils.GsonUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import java.util.concurrent.TimeUnit;

public class PaymentActivity extends BaseActivity<PaymentDelegate> {
    private final int WXPAY =101;
    private final int YWTPAY =102;
    private final int ZFBPAY =103;
    private int choice =WXPAY;
    private static final String APP_ID ="wx2920e5b3cf5c3b39";

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
        RxView.clicks(viewDelegate.get(R.id.iv_payment_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {

                    binder.work(viewDelegate,new RequestBean(278,1,"192.168.1.20"));
                    //viewDelegate.finish();
                });
        //支付选择
        RxView.clicks(viewDelegate.get(R.id.iv_payment_wx)).subscribe(aVoid -> {
            choice = WXPAY;
            setPayIcon();
        });
        RxView.clicks(viewDelegate.get(R.id.iv_payment_ywt)).subscribe(aVoid -> {
            choice = YWTPAY;
            setPayIcon();
        });
        RxView.clicks(viewDelegate.get(R.id.iv_payment_zfb)).subscribe(aVoid -> {
            choice = ZFBPAY;
            setPayIcon();
        });
        RxView.clicks(viewDelegate.get(R.id.btn_payment_pay)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    if (choice==WXPAY) {
                        api= WXAPIFactory.createWXAPI(this,APP_ID,false);
                        if (!api.isWXAppInstalled()){
                            Toast.makeText(this, "未安装微信", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        boolean b = api.registerApp(APP_ID);
                        Toast.makeText(this, "isssss"+b, Toast.LENGTH_SHORT).show();
                        WXBack bean = GsonUtils.GsonToBean(App.request.getData(), WXBack.class);
                        Log.d("ch","解析参数"+GsonUtils.GsonString(bean));
                        PayReq pay =new PayReq();
                        pay.appId = bean.getAppid();
                        Log.d("ch","getAppid"+bean.getAppid());
                        pay.partnerId =bean.getPartnerid();
                        Log.d("ch","partnerId"+bean.getPartnerid());
                        pay.prepayId =bean.getPrepayid();
                        Log.d("ch","prepayId"+bean.getPrepayid());
                        pay.packageValue = bean.getPackageX();
                        Log.d("ch","packageValue"+bean.getPackageX());
                        pay.nonceStr = bean.getNoncestr();
                        Log.d("ch","nonceStr"+bean.getNoncestr());
                        pay.timeStamp = bean.getTimestamp();
                        Log.d("ch","timeStamp"+bean.getTimestamp());
                        pay.sign = bean.getSign();
                        Log.d("ch","sign"+bean.getSign());
                        api.sendReq(pay);
                    }
                });
    }

    @Override
    public void initData() {


    }

    private void setPayIcon(){
        switch (choice){
            case 101:
                viewDelegate.setEnable(false,R.id.iv_payment_wx);
                viewDelegate.setEnable(true,R.id.iv_payment_ywt);
                viewDelegate.setEnable(true,R.id.iv_payment_zfb);
                break;
            case 102:
                viewDelegate.setEnable(false,R.id.iv_payment_ywt);
                viewDelegate.setEnable(true,R.id.iv_payment_wx);
                viewDelegate.setEnable(true,R.id.iv_payment_zfb);
                break;
            case 103:
                viewDelegate.setEnable(false,R.id.iv_payment_zfb);
                viewDelegate.setEnable(true,R.id.iv_payment_wx);
                viewDelegate.setEnable(true,R.id.iv_payment_ywt);
                break;
        }
    }
}
