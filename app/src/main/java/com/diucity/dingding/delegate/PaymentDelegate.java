package com.diucity.dingding.delegate;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
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
import java.util.List;

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

        setText(textSpan("支付 " + StringUtils.fmoney(getActivity().getIntent().getDoubleExtra("total", 0.0), 2) + "元"), R.id.tv_payment_pay);
        setText("确认支付 (" + StringUtils.fmoney(getActivity().getIntent().getDoubleExtra("total", 0.0), 2) + ")", R.id.btn_payment_pay);
        Picasso.with(getActivity()).load(getActivity().getIntent().getStringExtra("url")).resize(100, 100).transform(new Picassoloader()).into((ImageView) get(R.id.iv_payment_head));
        RecyclerView rv = get(R.id.rv_payment);
        rv.setAdapter(new PaymentAdapter(getActivity(), getList()));
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private ArrayList<CreateBack.DataBean.ScrapsBean> getList() {
        ArrayList<CreateBack.DataBean.ScrapsBean> list = (ArrayList<CreateBack.DataBean.ScrapsBean>) getActivity().getIntent().getSerializableExtra("list");
        ArrayList<CreateBack.DataBean.ScrapsBean> a = new ArrayList<>();
        for (CreateBack.DataBean.ScrapsBean bean : list) {
            if (bean.getQuantity() > 0) {
                a.add(bean);
            }
        }
        return a;
    }

    private List<InfoBack.DataBean.ScrapsBean> getList2(InfoBack info) {
        List<InfoBack.DataBean.ScrapsBean> list = info.getData().getScraps();
        List<InfoBack.DataBean.ScrapsBean> a = new ArrayList<>();
        for (InfoBack.DataBean.ScrapsBean bean : list) {
            if (bean.getQuantity() > 0) {
                a.add(bean);
            }
        }
        return a;
    }

    public void setDialog(InfoBack info) {
        setText(StringUtils.fmoney(info.getData().getAmount(), 2), R.id.tv_payment_total);
        setText(TimeUtils.getMinute(info.getData().getTime()), R.id.tv_payment_time);
        setText("订单号：" + info.getData().getOrder_no(), R.id.tv_payment_orderNo);
        RecyclerView rv = get(R.id.rv_payment2);
        rv.setAdapter(new DialogAdapter(getActivity(), getList2(info)));
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private SpannableString textSpan(String str) {
        String text = str;
        SpannableString textSpan = new SpannableString(text);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getActivity(), 20)), 3, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity(), R.color.text_orange)), 3, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return textSpan;
    }

    public void showResult(boolean is){

        setVisiable(false, R.id.arl_payment_pay);
        if (!is){
           setSrc(ContextCompat.getDrawable(getActivity(),R.mipmap.ic_cmm_error_big),R.id.iv_payment_src);
            TextView tv = get(R.id.tv_payment_success);
            tv.setTextColor(Color.parseColor("#F74C31"));
            setText("支付失败",R.id.tv_payment_success);
            setText("如果你的支付已到账\n24小时后内会回原路退回",R.id.tv_payment_total);
            setVisiable(false,R.id.tv_payment_time);
            setVisiable(false,R.id.ard_payment_detail);
            setVisiable(false,R.id.tv_payment_back);
            setVisiable(true,R.id.btn_payment_failed);

        }
        setVisiable(true, R.id.arl_payment);
    }
}
