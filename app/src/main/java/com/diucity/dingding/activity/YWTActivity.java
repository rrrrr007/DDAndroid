package com.diucity.dingding.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.diucity.dingding.R;
import com.diucity.dingding.entity.Back.DataBack;
import com.diucity.dingding.utils.GsonUtils;

import cmb.pb.util.CMBKeyboardFunc;

public class YWTActivity extends AppCompatActivity {
    private boolean ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ywt);
        findViewById(R.id.iv_ywt_back).setOnClickListener(v -> finish());

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
                //http://cmbls/cmbKeyboard?id=remitPwdDiv&password=true&keyboard=number&hint=&length=6&pointy=225&scrollTop=0&documentHeight=575&clientno=UI7tkvbgc40nu3GBHZ5AP7yMOzs=

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
                    new Handler().postDelayed(() -> {
                        YWTActivity.this.finish();
                    }, 3000);
                }
                super.onPageFinished(view, url);
            }
        });
        wv.postUrl(bean.getUrl(), ("jsonRequestData=" + bean.getBody()).getBytes());
    }

    @Override
    protected void onDestroy() {
        setResult(RESULT_OK, new Intent().putExtra("ok", ok));
        super.onDestroy();
    }
}


