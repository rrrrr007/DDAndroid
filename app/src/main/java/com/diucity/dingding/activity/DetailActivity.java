package com.diucity.dingding.activity;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.DetailDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;
import com.tencent.smtt.sdk.WebView;

import java.util.concurrent.TimeUnit;

public class DetailActivity extends BaseActivity<DetailDelegate> {
    private WebView webView;

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected Class<DetailDelegate> getDelegateClass() {
        return DetailDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_detail_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
    }

    @Override
    public void initData() {
        webView = viewDelegate.get(R.id.web_detail);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        webView = null;
        super.onDestroy();
    }
}
