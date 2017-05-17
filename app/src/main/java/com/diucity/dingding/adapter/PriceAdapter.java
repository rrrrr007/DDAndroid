package com.diucity.dingding.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.TextView;

import com.diucity.dingding.utils.ActivityUtils;
import com.diucity.dingding.R;
import com.diucity.dingding.entity.Back.TodayBack;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class PriceAdapter extends BaseAdapter<TodayBack.DataBean.ScrapsBean> {

    public PriceAdapter(Context context, ArrayList<TodayBack.DataBean.ScrapsBean> model) {
        super(context, model);
    }

    @Override
    public int getId() {
        return R.layout.adapter_price;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getView(R.id.adapter_tv_price_title).setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        TextView title = holder.getView(R.id.adapter_tv_price_title);
        title.setText("今日最新报价");
        TextView name = holder.getView(R.id.adapter_tv_price_name);
        name.setText(getModel().get(position).getName() + "/" + getModel().get(position).getUnit());
        TextView content = holder.getView(R.id.adapter_tv_price_content);
        content.setText("买 ￥" + getModel().get(position).getBuy_price() + "/  卖 ￥" + getModel().get(position).getSell_price());
        TextView tv = holder.getView(R.id.adapter_tv_price_difference);
        tv.setText(textSpan(String.format("%.2f", getModel().get(position).getSell_price() - getModel().get(position).getBuy_price())+"元"));

    }

    private SpannableString textSpan(String str) {
        String text = str;
        SpannableString textSpan = new SpannableString(text);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(), 40)), 0, text.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(), 15)), text.length() - 1, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return textSpan;
    }


}
