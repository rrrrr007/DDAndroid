package com.diucity.dingding.delegate;

import android.app.Dialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.DialogAdapter;
import com.diucity.dingding.adapter.PaymentAdapter;
import com.diucity.dingding.entity.Back.CreateBack;
import com.diucity.dingding.entity.Back.InfoBack;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.ActivityUtils;
import com.diucity.dingding.utils.Picassoloader;
import com.diucity.dingding.utils.StringUtils;
import com.diucity.dingding.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public class PaymentDelegate extends AppDelegate {
    private Dialog dialog;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public boolean needShow() {
        return true;
    }

    @Override
    public void initWidget() {
        setEnable(false, R.id.iv_payment_wx);
        setText("订单号: " + getActivity().getIntent().getStringExtra("number"), R.id.tv_payment_number);
        setText(textSpan("支付 " + StringUtils.getDoubleString(getActivity().getIntent().getDoubleExtra("total", 0.0)) + "元"), R.id.tv_payment_pay);
        setText("确认支付 (" + StringUtils.getDoubleString(getActivity().getIntent().getDoubleExtra("total", 0.0)) + ")", R.id.btn_payment_pay);
        Picasso.with(getActivity()).load(getActivity().getIntent().getStringExtra("url")).resize(100, 100).transform(new Picassoloader()).into((ImageView) get(R.id.iv_payment_head));
        ArrayList<CreateBack.DataBean.ScrapsBean> list = (ArrayList<CreateBack.DataBean.ScrapsBean>) getActivity().getIntent().getSerializableExtra("list");
        RecyclerView rv = get(R.id.rv_payment);
        rv.setAdapter(new PaymentAdapter(getActivity(), list));
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void setDialog(InfoBack info) {
        setText(StringUtils.getDoubleString(info.getData().getAmount()), R.id.tv_payment_total);
        setText(TimeUtils.getMinute(info.getData().getTime()), R.id.tv_payment_time);
        setText("订单号：" + info.getData().getOrder_no(), R.id.tv_payment_orderNo);
        RecyclerView rv = get(R.id.rv_payment2);
        rv.setAdapter(new DialogAdapter(getActivity(), info.getData().getScraps()));
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private SpannableString textSpan(String str){
        String text = str;
        SpannableString textSpan = new SpannableString(text);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getActivity(), 20)), 3, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity(),R.color.text_orange)),3, text.length(),Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return textSpan;
    }
}
