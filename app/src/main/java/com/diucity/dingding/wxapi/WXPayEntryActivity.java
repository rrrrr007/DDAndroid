package com.diucity.dingding.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

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

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

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
        TextView viewById = (TextView) findViewById(R.id.tv_zz);
        if (resp.errCode==0){
            viewById.setText("支付成功,3s后返回");
            new Handler().postDelayed(() -> {
                ((PaymentActivity)App.getAcitvity("activity.PaymentActivity")).showSuccess();
                WXPayEntryActivity.this.finish();
            },3000);
        }else {
            viewById.setText("支付失败,3s后返回");
            new Handler().postDelayed(() -> {
                ((PaymentActivity)App.getAcitvity("activity.PaymentActivity")).showFailure();
                WXPayEntryActivity.this.finish();
            },3000);
        }

    }
}
