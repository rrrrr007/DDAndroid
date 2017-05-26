package com.diucity.dingding.delegate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Window;
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
    private AlertDialog alertDialog;

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
    public int getBillID() {
        return getActivity().getIntent().getIntExtra("billId",0);
    }

    @JavascriptInterface
    public void callService(String number){
        showCallDialog(number);
    }

    public void showCallDialog(String string) {
        Intent intent= new Intent(Intent.ACTION_DIAL);
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
        if (!TextUtils.isEmpty(string)){
            alertDialog.setMessage("联系客服（"+string+"）");
            intent.setData(Uri.parse("tel:" + string));
        }else {
            alertDialog.setMessage("联系客服（400-8032039）");
            intent.setData(Uri.parse("tel:" + "400-8032023"));
        }
        if (!alertDialog.isShowing()){
            alertDialog.show();
        }
    }
}
