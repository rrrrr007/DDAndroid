package com.diucity.dingding.delegate;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.widget.TextView;

import com.diucity.dingding.app.App;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class WebDelegate extends AppDelegate {
    Dialog dialog;

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
        wv.loadUrl("http://192.168.3.18:8080/#/withdraw");
        wv.addJavascriptInterface(WebDelegate.this, "android");
        set.setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());
    }


    @JavascriptInterface
    public String getToken(){
        return App.user.getData().getAuth_token();
    }

    @JavascriptInterface
    public void show() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_login, null);
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
    }

    @JavascriptInterface
    public void show(String str) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_login, null);
        if (!TextUtils.isEmpty(str)) {
            TextView text = (TextView) view.findViewById(R.id.tv_dialog_text);
            text.setText(str);
        }
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
    }
}
