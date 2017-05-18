package com.diucity.dingding.activity;

import com.diucity.dingding.app.App;
import com.diucity.dingding.binder.WalletBinder;
import com.diucity.dingding.delegate.WalletDelegate;
import com.diucity.dingding.entity.Send.SummaryBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.R;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

public class WalletActivity extends BaseActivity<WalletDelegate> {


    @Override
    protected Class<WalletDelegate> getDelegateClass() {
        return WalletDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new WalletBinder();
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
       /* //绑定银行卡
        RxView.clicks(viewDelegate.get(R.id.ll_wallet_bankCard)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this,WebActivity.class));
                });*/
        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_wallet_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.finish();
                });

        //设置中心
        RxView.clicks(viewDelegate.get(R.id.iv_wallet_options)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.startActivity(OptionsActivity.class);
                });
        //账单明细
        RxView.clicks(viewDelegate.get(R.id.ll_wallet)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.startActivity(RecordActivity.class);
                });

        //提现
        RxView.clicks(viewDelegate.get(R.id.btn_wallet)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.startActivity(WithdrawActivity.class);
                });
    }

    @Override
    public void initData() {
        binder.work(viewDelegate,new SummaryBean(App.user.getData().getRecycler_id(),App.user.getData().getAuth_token()));
    }
}
