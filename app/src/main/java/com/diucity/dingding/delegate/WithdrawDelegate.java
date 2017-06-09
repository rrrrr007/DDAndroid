package com.diucity.dingding.delegate;

import android.app.Dialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.widget.TextView;
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
 * Created by Administrator on 2017/4/20 0020.
 */
public class WithdrawDelegate extends AppDelegate {
    Dialog dialog;
    private ProgressView pv;
    private WebView wv;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    public boolean needShow() {
        return true;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        pv = get(R.id.pv_withdraw);
        wv = get(R.id.webView_withdraw);
        WebSettings set = wv.getSettings();
        wv.loadUrl(Api.WEBURL + "#/withdraw");
        wv.addJavascriptInterface(WithdrawDelegate.this, "android");
        set.setJavaScriptEnabled(true);
        set.setDomStorageEnabled(true);
        set.setBuiltInZoomControls(true);
        set.setUseWideViewPort(true);
        set.setLoadWithOverviewMode(true);
        set.setSaveFormData(false);
        set.setSavePassword(false);
        set.setSupportZoom(false);
        set.setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv.requestFocus();
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.d("ch", "p" + newProgress);
                pv.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView webView, String s) {
                if (s.contains("details")) {
                    App.getAcitvity("activity.WalletActivity").doAction1();
                }
                super.onPageFinished(webView, s);
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
