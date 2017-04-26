package com.diucity.dingding.delegate;

import android.graphics.Bitmap;
import android.util.Log;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.widget.ProgressView;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class DetailDelegate extends AppDelegate {
    private WebView webView;
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public boolean needShow() {
        return true;
    }

    @Override
    public void initWidget() {
        webView = get(R.id.web_detail);
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.loadUrl("https://www.baidu.com/");
        webView.setWebViewClient(new WebViewClient() {
            // 防止加载网页时调起系统浏览器
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                ((ProgressView)get(R.id.pv_detail)).reset();
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                ((ProgressView)get(R.id.pv_detail)).reset();
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView webView, int i) {
                Log.d("ch","i"+i);
                super.onProgressChanged(webView, i);
                ((ProgressView)get(R.id.pv_detail)).setProgress(i);
            }
        });
    }

    public void setNull(){
        webView = null;
    }
}
