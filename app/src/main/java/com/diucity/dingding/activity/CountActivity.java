package com.diucity.dingding.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.CountAdapter;
import com.diucity.dingding.app.App;
import com.diucity.dingding.delegate.CountDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.ActivityUtils;
import com.diucity.dingding.utils.KeyboardUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.liaoinstan.springview.utils.DensityUtil;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

public class CountActivity extends BaseActivity<CountDelegate> {
    private int index = 0;
    private boolean first = true;
    private RecyclerView rv;
    private GridLayoutManager manager;
    private CountAdapter adapter;
    private EditText edt;

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected Class<CountDelegate> getDelegateClass() {
        return CountDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        //edt显示位置调整
        KeyboardUtils.getSoftKeyboardHeight(viewDelegate.getRootView(), height -> {
            App.setHeight(height);
        });
        KeyboardUtils.doMonitorSoftKeyWord(viewDelegate.getRootView(), isShow -> {
            View view = viewDelegate.get(R.id.rl_count_edt);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
            if (isShow)
                lp.bottomMargin =  App.getHeight();
            else
                lp.bottomMargin = 0;
            view.setLayoutParams(lp);
        });
        //Item点击
        adapter.setListener(position -> {
            smoothMoveToPosition(position);
            viewDelegate.setText((spite("单价\n0." + position + "0/斤")), R.id.tv_count_oneprice);
            viewDelegate.setText(adapter.getText(), R.id.edt_count);

        });
        //edt内容改变item内容
        RxTextView.afterTextChangeEvents(edt).subscribe(textChangeEvent -> {
                    if (!first){
                        adapter.setText(textChangeEvent.editable().toString());
                        edt.setSelection(edt.getText().length());
                    }
                    else
                        first = false;

                });
        //光标滞后
        RxView.clicks(viewDelegate.get(R.id.tv_count_account)).throttleFirst(1, TimeUnit.SECONDS).subscribe(aVoid -> {
            Intent intent = new Intent(this,AccountActivity.class);
            viewDelegate.startActivity(intent);
        });
    }

    public SpannableString spite(String str) {
        SpannableString textSpan = new SpannableString(str);
        textSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#031912")), 3, str.length() - 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return textSpan;
    }

    private void smoothMoveToPosition(int position) {
        int firstPosition = manager.findFirstVisibleItemPosition();
        int lastPosition = manager.findLastVisibleItemPosition();
        if (position <= lastPosition) {
            int top = rv.getChildAt(position - firstPosition).getTop();
            rv.smoothScrollBy(0, top);
        }
    }

    @Override
    public void initData() {
        rv = viewDelegate.get(R.id.rv_count);
        manager = (GridLayoutManager) rv.getLayoutManager();
        adapter = (CountAdapter) rv.getAdapter();
        edt = viewDelegate.get(R.id.edt_count);
    }
}
