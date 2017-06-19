package com.diucity.dingding.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.entity.Back.DescBack;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class SellAdapter extends BaseAdapter<DescBack.DataBean.ScrapsBean> {

    public SellAdapter(Context context, List<DescBack.DataBean.ScrapsBean> model) {
        super(context, model);
    }

    @Override
    public int getId() {
        return R.layout.adapter_payment;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TextView name = holder.getView(R.id.adapter_tv_payment_name);
        name.setText(getModel().get(position).getName());
        TextView weight = holder.getView(R.id.adapter_tv_payment_weight);
        weight.setText(getModel().get(position).getQuantity() + getModel().get(position).getUnit());
        TextView much = holder.getView(R.id.adapter_tv_payment_much);
        much.setText(getModel().get(position).getAmount() + "元");
        if (position == getItemCount() - 1) {
            name.setTextColor(Color.parseColor("#f74c31"));
            weight.setTextColor(Color.parseColor("#f74c31"));
            much.setTextColor(Color.parseColor("#f74c31"));
        }
    }


}
