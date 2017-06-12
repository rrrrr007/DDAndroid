package com.diucity.dingding.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.activity.PaymentActivity;
import com.diucity.dingding.app.App;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI api;
    public static final String APP_ID = "wx2920e5b3cf5c3b39";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);
        api = WXAPIFactory.createWXAPI(this, APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        Log.d("ch", ", openId = " + req.openId);
    }

    /**
     * 得到支付结果回调
     */
    @Override
    public void onResp(BaseResp resp) {
        Log.d("ch", "onPayFinish, errCode = " + resp.errCode);// 支付结果码
        if (resp.errCode == 0) {
            WXPayEntryActivity.this.finish();
            ((PaymentActivity) App.getAcitvity("activity.PaymentActivity")).showSuccess();
        } else {
            WXPayEntryActivity.this.finish();
            ((PaymentActivity) App.getAcitvity("activity.PaymentActivity")).showFailure();
        }

    }
}
