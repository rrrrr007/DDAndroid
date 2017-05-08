package com.diucity.dingding.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Window;
import android.widget.EditText;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.CountAdapter;
import com.diucity.dingding.delegate.CountDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.ActivityUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;


public class CountActivity extends BaseActivity<CountDelegate> {
    private AlertDialog alertDialog;
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

        //Item点击
        adapter.setListener(position -> {
            smoothMoveToPosition(position);
            viewDelegate.setText((spite("单价\n0." + position + "0/斤")), R.id.tv_count_oneprice);
            viewDelegate.setText(adapter.getText(), R.id.edt_count);

        });
        //edt内容改变item内容
        RxTextView.afterTextChangeEvents(edt).subscribe(textChangeEvent -> {
            if (!first) {
                adapter.setText(textChangeEvent.editable().toString());
                edt.setSelection(edt.getText().length());

            } else
                first = false;

        });
        //结算
        RxView.clicks(viewDelegate.get(R.id.tv_count_payment)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
            showCallDialog();
        });

        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_count_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.startActivity(CaptureActivity.class);
                    viewDelegate.finish();
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

    public void showCallDialog() {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("是否生成订单，进行结算")
                    .setPositiveButton("结算", (dialog, which) -> {
                        alertDialog.dismiss();
                        Intent intent = new Intent(this, PaymentActivity.class);
                        viewDelegate.startActivity(intent);
                    })
                    .setNegativeButton("取消", (dialog2, which) -> alertDialog.dismiss())
                    .create();
            Window window = alertDialog.getWindow();
            window.setWindowAnimations(R.style.dialog_style);
        }
        alertDialog.show();
    }

    public void getall(){

    }

    public SpannableString textSpan(Double d){
        String text = String.format("%.2f",d)+"元";
        SpannableString textSpan = new SpannableString(text);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(this, 12)), text.length() - 1, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return textSpan;
    }
}
