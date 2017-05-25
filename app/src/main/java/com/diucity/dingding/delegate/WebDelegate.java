package com.diucity.dingding.delegate;

import android.webkit.JavascriptInterface;

import com.diucity.dingding.R;
import com.diucity.dingding.api.Api;
import com.diucity.dingding.app.App;
import com.diucity.dingding.entity.User;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.GsonUtils;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class WebDelegate extends AppDelegate {


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        WebView wv = get(R.id.webView_web);
        WebSettings set = wv.getSettings();
        wv.loadUrl(Api.WEBURL+"#/billing-details");
        wv.addJavascriptInterface(WebDelegate.this, "android");
        set.setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());
    }

    @JavascriptInterface
    public String getUserData() {
        String s = GsonUtils.GsonString(new User(App.user.getData().getRecycler_id(), App.user.getData().getAuth_token()));
        return s;
    }

    @JavascriptInterface
    public int getBillId() {
        return 11;
    }
}
