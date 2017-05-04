package com.diucity.dingding.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.entity.Back.BasketBack;
import com.diucity.dingding.entity.Back.TodayBack;
import com.diucity.dingding.utils.ActivityUtils;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class BasketAdapter extends BaseAdapter<BasketBack.Data.DataBean> {


    private List<TodayBack.DataBean.ScrapsBean> price;

    public BasketAdapter(Context context, ArrayList<BasketBack.Data.DataBean> model) {
        super(context, model);
        getPrice();
    }

    @Override
    public int getId() {
        return R.layout.adapter_price;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (price == null) return;
        TodayBack.DataBean.ScrapsBean bean = getBean(getModel().get(position).getScrap_id());
        if (bean == null) return;
        holder.getView(R.id.adapter_tv_price_title).setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        TextView name = holder.getView(R.id.adapter_tv_price_name);
        name.setText(bean.getName());
        TextView content = holder.getView(R.id.adapter_tv_price_content);
        content.setText("预估收益" + String.format("%.2f", (getModel().get(position).getQuantity() * bean.getSell_price())));
        TextView tv = holder.getView(R.id.adapter_tv_price_difference);
        tv.setText(textSpan(getModel().get(position).getQuantity() + bean.getUnit()));
        TextView title = holder.getView(R.id.adapter_tv_price_title);
        title.setText("预估总收益：" + getAll() + "元");
    }

    private SpannableString textSpan(String str) {
        String text = str;
        SpannableString textSpan = new SpannableString(text);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(), 40)), 0, text.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(), 15)), text.length() - 1, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return textSpan;
    }

    private TodayBack.DataBean.ScrapsBean getBean(int id) {
        for (TodayBack.DataBean.ScrapsBean bean : price) {
            if (bean.getScrap_id() == id) {
                return bean;
            }
        }
        return null;
    }

    private String getAll() {
        double all = 0;
        for (BasketBack.Data.DataBean bean : getModel()) {
            if (getBean(bean.getScrap_id())==null)break;
            all += bean.getQuantity() * getBean(bean.getScrap_id()).getSell_price();
        }
        return String.format("%.2f", all);
    }

    public void getPrice() {
        String today = SpUtils.getString(getContext(), SpUtils.TODAY);
        if (TextUtils.isEmpty(today)) return;
        price = GsonUtils.GsonToBean(today, TodayBack.class).getData().getScraps();
    }

    @Override
    public void updateBySet(List<BasketBack.Data.DataBean> model) {
        getPrice();
        super.updateBySet(model);
    }

    @Override
    public void updateByAdd(List<BasketBack.Data.DataBean> model) {
        getPrice();
        super.updateByAdd(model);
    }
}
