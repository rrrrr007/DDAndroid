package com.diucity.dingding.delegate;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.PaymentAdapter;
import com.diucity.dingding.entity.Back.CreateBack;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.Picassoloader;
import com.diucity.dingding.utils.StringUtils;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public class PaymentDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        setEnable(false,R.id.iv_payment_wx);
        setText("订单号: "+getActivity().getIntent().getStringExtra("number"),R.id.tv_payment_number);
        setText("支付 "+ StringUtils.getDoubleString(getActivity().getIntent().getDoubleExtra("total",0.0))+"元",R.id.tv_payment_pay);
        setText("确认支付 （"+ StringUtils.getDoubleString(getActivity().getIntent().getDoubleExtra("total",0.0))+")",R.id.btn_payment_pay);
        Picasso.with(getActivity()).load(getActivity().getIntent().getStringExtra("url")).resize(100,100).transform(new Picassoloader()).into((ImageView) get(R.id.iv_payment_head));
        ArrayList<CreateBack.DataBean.ScrapsBean> list = (ArrayList<CreateBack.DataBean.ScrapsBean>) getActivity().getIntent().getSerializableExtra("list");
        RecyclerView rv = get(R.id.rv_payment);
        rv.setAdapter(new PaymentAdapter(getActivity(),list));
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
