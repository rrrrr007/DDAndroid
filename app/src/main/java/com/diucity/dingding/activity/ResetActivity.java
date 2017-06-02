package com.diucity.dingding.activity;

import android.view.View;
import android.widget.EditText;

import com.diucity.dingding.R;
import com.diucity.dingding.app.App;
import com.diucity.dingding.binder.ResetBinder;
import com.diucity.dingding.delegate.ResetDelegate;
import com.diucity.dingding.entity.Send.ChangeBean;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

public class ResetActivity extends BaseActivity<ResetDelegate> implements View.OnFocusChangeListener {

    private boolean oldLength;
    private boolean newLength;
    private boolean affirmLength;

    @Override
    public DataBinder getDataBinder() {
        return new ResetBinder();
    }

    @Override
    protected Class<ResetDelegate> getDelegateClass() {
        return ResetDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        //完成输入
        RxView.clicks(viewDelegate.get(R.id.btn_reset_finish)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    String old = ((EditText) viewDelegate.get(R.id.edt_reset_old)).getText().toString();
                    String nnew = ((EditText) viewDelegate.get(R.id.edt_reset_new)).getText().toString();
                    String affirm = ((EditText) viewDelegate.get(R.id.edt_reset_affirm)).getText().toString();
                    if (!nnew.equals(affirm)) {
                        viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 3, "2次密码不一致");
                    } else {
                        binder.work(viewDelegate, new ChangeBean(App.user.getData().getRecycler_id(), old, nnew, App.user.getData().getAuth_token()));
                    }


                });

        //edt字段监听
        RxTextView.textChanges(viewDelegate.get(R.id.edt_reset_old)).subscribe(charSequence -> {
            oldLength = charSequence.length() >= 6;
            viewDelegate.setEnable(oldLength && newLength && affirmLength, R.id.btn_reset_finish);
            viewDelegate.textChange();
        });
        RxTextView.textChanges(viewDelegate.get(R.id.edt_reset_new)).subscribe(charSequence -> {
            newLength = charSequence.length() >= 6;
            viewDelegate.setEnable(oldLength && newLength && affirmLength, R.id.btn_reset_finish);
            viewDelegate.textChange();
        });
        RxTextView.textChanges(viewDelegate.get(R.id.edt_reset_affirm)).subscribe(charSequence -> {
            affirmLength = charSequence.length() >= 6;
            viewDelegate.setEnable(oldLength && newLength && affirmLength, R.id.btn_reset_finish);
            viewDelegate.textChange();
        });


        //下划线
        viewDelegate.get(R.id.edt_reset_old).setOnFocusChangeListener(this);
        viewDelegate.get(R.id.edt_reset_new).setOnFocusChangeListener(this);
        viewDelegate.get(R.id.edt_reset_affirm).setOnFocusChangeListener(this);

        RxView.clicks(viewDelegate.get(R.id.iv_reset_icon1)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    ((EditText) viewDelegate.get(R.id.edt_reset_old)).setText("");
                });
        RxView.clicks(viewDelegate.get(R.id.iv_reset_icon2)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    ((EditText) viewDelegate.get(R.id.edt_reset_new)).setText("");
                });
        RxView.clicks(viewDelegate.get(R.id.iv_reset_icon3)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    ((EditText) viewDelegate.get(R.id.edt_reset_affirm)).setText("");
                });
        //返回
        RxView.clicks(viewDelegate.get(R.id.tv_reset_back)).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.stay, R.anim.over);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        viewDelegate.lineChange(v, hasFocus);
        viewDelegate.textChange();
    }


}
