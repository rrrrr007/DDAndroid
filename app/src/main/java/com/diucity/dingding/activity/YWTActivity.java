package com.diucity.dingding.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.diucity.dingding.R;
import com.diucity.dingding.app.App;
import com.diucity.dingding.entity.Back.DataBack;
import com.diucity.dingding.utils.GsonUtils;

import cmb.pb.util.CMBKeyboardFunc;

public class YWTActivity extends AppCompatActivity {
    private boolean ok =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ywt);
        findViewById(R.id.iv_ywt_back).setOnClickListener(v -> {
            YWTActivity.this.finish();
            if (ok) {
                ((PaymentActivity) (App.getAcitvity("activity.PaymentActivity"))).showSuccess();
            } else {
                ((PaymentActivity) (App.getAcitvity("activity.PaymentActivity"))).showFailure();
            }
        });

        DataBack bean = GsonUtils.GsonToBean(getIntent().getStringExtra("request"), DataBack.class);
        Log.d("ch", "WithdrawDelegate" + GsonUtils.GsonString(bean));
        WebView wv = (WebView) findViewById(R.id.webView_ywt);
        WebSettings set = wv.getSettings();
        set.setBuiltInZoomControls(true);
        set.setUseWideViewPort(true);
        set.setLoadWithOverviewMode(true);
        set.setJavaScriptEnabled(true);
        set.setSaveFormData(false);
        set.setSavePassword(false);
        set.setSupportZoom(false);
        wv.requestFocus();
        wv.setContentDescription("Content-Type:application/x-www-form-urlencoded");
        wv.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                // 使用当前的WebView加载页面
                CMBKeyboardFunc kbFunc = new CMBKeyboardFunc(YWTActivity.this);
                if (kbFunc.HandleUrlCall(wv, url) == false) {
                    return super.shouldOverrideUrlLoading(view, url);
                } else {
                    return true;
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.contains("MB_EUserP_PayOK")) {
                    ok = true;
                    YWTActivity.this.finish();
                    if (ok) {
                        ((PaymentActivity) (App.getAcitvity("activity.PaymentActivity"))).showSuccess();
                    } else {
                        ((PaymentActivity) (App.getAcitvity("activity.PaymentActivity"))).showFailure();
                    }
                }
                super.onPageFinished(view, url);
            }
        });
        wv.postUrl(bean.getUrl(), ("jsonRequestData=" + bean.getBody()).getBytes());
    }

    @Override
    public void onBackPressed() {
        YWTActivity.this.finish();
        ((PaymentActivity) (App.getAcitvity("activity.PaymentActivity"))).showFailure();
    }

}


