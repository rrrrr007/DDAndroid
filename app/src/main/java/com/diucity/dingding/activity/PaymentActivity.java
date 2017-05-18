package com.diucity.dingding.activity;

import android.util.Log;

import com.diucity.dingding.app.App;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.R;
import com.diucity.dingding.delegate.PaymentDelegate;
import com.diucity.dingding.wxapi.WXPayEntryActivity;
import com.jakewharton.rxbinding.view.RxView;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

public class PaymentActivity extends BaseActivity<PaymentDelegate> {
    private final int WXPAY =101;
    private final int YWTPAY =102;
    private final int ZFBPAY =103;
    private int choice =WXPAY;
    private static final String APP_ID ="wxd930ea5d5a258f4f";
    private IWXAPI api;


    @Override
    public DataBinder getDataBinder() {
        return null;
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
                    viewDelegate.finish();
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
                        WXTextObject textObject = new WXTextObject();
                        textObject.text ="";
                        WXMediaMessage msg =new WXMediaMessage();
                        msg.mediaObject = textObject;
                        msg.description = "";
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = String.valueOf(System.currentTimeMillis());
                        req.message = msg;
                        api.sendReq(req);
                    }
                });
    }

    @Override
    public void initData() {
        api= WXAPIFactory.createWXAPI(this,APP_ID,true);
        api.registerApp(APP_ID);
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
