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
import com.diucity.dingding.entity.Back.ScrapsBack;
import com.diucity.dingding.entity.Back.TodayBack;
import com.diucity.dingding.utils.ActivityUtils;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SpUtils;
import com.diucity.dingding.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class BasketAdapter extends BaseAdapter<ScrapsBack.Data.Scraps> {
    private BasketBack basket;
    private TodayBack today;

    public BasketAdapter(Context context, ArrayList<ScrapsBack.Data.Scraps> model) {
        super(context, model);
    }

    @Override
    public int getId() {
        return R.layout.adapter_price;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getView(R.id.adapter_tv_price_title).setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        TextView name = holder.getView(R.id.adapter_tv_price_name);
        name.getPaint().setFakeBoldText(true);
        name.setText(getModel().get(position).getName());
        TextView content = holder.getView(R.id.adapter_tv_price_content);
        content.setText("预估收益 ￥" + StringUtils.fmoney(getQuantity(position) * getPrice(position),2));
        TextView tv = holder.getView(R.id.adapter_tv_price_difference);
        if (getModel().get(position).getUnit().equals("斤")) {
            tv.setText(textSpan(StringUtils.getDoubleString(getQuantity(position)) + getModel().get(position).getUnit()));
        } else {
            tv.setText(textSpan(StringUtils.getIntString(getQuantity(position)) + getModel().get(position).getUnit()));
        }

        TextView title = holder.getView(R.id.adapter_tv_price_title);
        title.setText("预估总收益：" + getAll() + "元");
    }

    @Override
    public int getItemCount() {
        return getModel().size();

    }

    private double getQuantity(int positon) {
        if (basket==null) return 0;
        int id = getModel().get(positon).getScrap_id();
        for (BasketBack.Data.DataBean bean : basket.getData().getData()) {
            if (bean.getScrap_id() == id) {
                return bean.getQuantity();
            }
        }
        return 0;
    }

    private double getPrice(int positon) {
        if (today==null) return 0;
        int id = getModel().get(positon).getScrap_id();
        for (TodayBack.DataBean.ScrapsBean bean : today.getData().getScraps()) {
            if (bean.getScrap_id() == id) {
                return bean.getSell_price();
            }
        }
        return 0;
    }

    private SpannableString textSpan(String str) {
        String text = str;
        SpannableString textSpan = new SpannableString(text);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(), 40)), 0, text.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(), 15)), text.length() - 1, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return textSpan;
    }


    private String getAll() {
        double all = 0;
        for (int i = 0; i < getModel().size() ;i++) {
            all += getQuantity(i) * getPrice(i);
        }
        return StringUtils.fmoney(all,2);
    }



    private void getToday() {
        String str1 = SpUtils.getString(getContext(), SpUtils.TODAY);
        if (TextUtils.isEmpty(str1)) return;
        today = GsonUtils.GsonToBean(str1, TodayBack.class);
    }

    public void setBasket(BasketBack basket) {
        this.basket = basket;
        getToday();
        notifyDataSetChanged();
    }

    @Override
    public void updateBySet(List<ScrapsBack.Data.Scraps> model) {
        super.updateBySet(model);
    }

    @Override
    public void updateByAdd(List<ScrapsBack.Data.Scraps> model) {
        super.updateByAdd(model);
    }
}
