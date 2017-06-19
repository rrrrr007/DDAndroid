package com.diucity.dingding.delegate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.diucity.dingding.R;
import com.diucity.dingding.api.Api;
import com.diucity.dingding.app.App;
import com.diucity.dingding.entity.User;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.widget.ProgressView;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class WebDelegate extends AppDelegate {
    private AlertDialog alertDialog;
    private ProgressView pv;
    WebView wv;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public boolean needShow() {
        return true;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        pv = get(R.id.pv_web);
        wv = get(R.id.webView_web);
        WebSettings set = wv.getSettings();
        wv.loadUrl(Api.WEBURL + "#/billing-details");
        wv.addJavascriptInterface(WebDelegate.this, "android");
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        set.setUseWideViewPort(true);
        set.setLoadWithOverviewMode(true);
        set.setSaveFormData(false);
        set.setSavePassword(false);
        set.setSupportZoom(false);
        set.setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv.requestFocus();
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView webView, String s) {
                if (s.contains("trade-details")) {
                    setText("交易详情", R.id.tv_detail_title);
                } else {
                    setText("账单详情", R.id.tv_detail_title);
                }
                super.onPageFinished(webView, s);
            }
        });
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pv.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });

    }

    @JavascriptInterface
    public String getUserData() {
        String s = GsonUtils.GsonString(new User(App.user.getData().getRecycler_id(), App.user.getData().getAuth_token()));
        return s;
    }

    @JavascriptInterface
    public void reload() {
        Toast.makeText(getActivity(), "reload", Toast.LENGTH_SHORT).show();
        wv.reload();
    }

    @JavascriptInterface
    public int getBillID() {
        return getActivity().getIntent().getIntExtra("billId", 0);
    }

    @JavascriptInterface
    public void callService(String number) {
        showCallDialog(number);
    }

    public void showCallDialog(String string) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("提示")

                    .setPositiveButton("拨打", (dialog, which) -> {
                        alertDialog.dismiss();

                        startActivity(intent);
                    })
                    .setNegativeButton("取消", (dialog2, which) -> alertDialog.dismiss()).create();
            Window window = alertDialog.getWindow();
            window.setWindowAnimations(R.style.dialog_style);
        }
        if (!TextUtils.isEmpty(string)) {
            alertDialog.setMessage("联系客服（" + string + "）");
            intent.setData(Uri.parse("tel:" + string));
        } else {
            alertDialog.setMessage("联系客服（400-8032039）");
            intent.setData(Uri.parse("tel:" + "400-8032023"));
        }
        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }
    }
}
