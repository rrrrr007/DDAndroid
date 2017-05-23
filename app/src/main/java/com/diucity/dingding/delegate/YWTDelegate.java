package com.diucity.dingding.delegate;

import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.diucity.dingding.R;
import com.diucity.dingding.app.App;
import com.diucity.dingding.entity.Back.DataBack;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.GsonUtils;

import cmb.pb.util.CMBKeyboardFunc;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class YWTDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_ywt;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        DataBack bean = GsonUtils.GsonToBean(App.request.getData(), DataBack.class);
        Log.d("ch", "WithdrawDelegate" + GsonUtils.GsonString(bean));
        WebView wv = get(R.id.webView_ywt);
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
                CMBKeyboardFunc kbFunc = new CMBKeyboardFunc(getActivity());
                if (kbFunc.HandleUrlCall(wv, url) == false) {
                    return super.shouldOverrideUrlLoading(view, url);
                } else {
                    return true;
                }
            }
        });
        wv.postUrl(bean.getUrl(), ("jsonRequestData=" + bean.getBody()).getBytes());
    }
}
