package com.diucity.dingding.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.SellDelegate;
import com.diucity.dingding.persent.DataBinder;

public class SellActivity extends BaseActivity<SellDelegate> {

    @Override
    protected Class<SellDelegate> getDelegateClass() {
        return SellDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }
}
