package com.diucity.dingding.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.AccountDelegate;
import com.diucity.dingding.persent.DataBinder;

public class AccountActivity extends BaseActivity<AccountDelegate> {


    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected Class<AccountDelegate> getDelegateClass() {
        return AccountDelegate.class;
    }
}
