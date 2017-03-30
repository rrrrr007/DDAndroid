package com.diucity.dingding.delegate;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;

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
        ((TextView) get(R.id.toolbar)).setText("绑定银行卡");
        WebView wv = get(R.id.webView_web);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("http://www.baidu.com/");
        wv.addJavascriptInterface(WebDelegate.this, "android");
        wv.setWebViewClient(new WebViewClient());
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
