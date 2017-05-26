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

public class BasketAdapter extends BaseAdapter<BasketBack.Data.DataBean> {
    private ScrapsBack scraps;
    private TodayBack today;

    public BasketAdapter(Context context, ArrayList<BasketBack.Data.DataBean> model) {
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
        name.setText(getScrapItem(position).getName());
        TextView content = holder.getView(R.id.adapter_tv_price_content);
        double price = 0;
        price = getTodayItem(position)==null?0:getTodayItem(position).getSell_price();
        content.setText("预估收益" + StringUtils.getDoubleString((getModel().get(position).getQuantity() * price)));
        TextView tv = holder.getView(R.id.adapter_tv_price_difference);
        tv.setText(textSpan(getModel().get(position).getQuantity() + getScrapItem(position).getUnit()));
        TextView title = holder.getView(R.id.adapter_tv_price_title);
        title.setText("预估总收益：" + getAll() + "元");
    }

    public void getScraps() {
        String str = SpUtils.getString(getContext(), SpUtils.SCRAPS);
        if (TextUtils.isEmpty(str)) return;
        scraps = GsonUtils.GsonToBean(str, ScrapsBack.class);
    }

    private void getToday() {
        String str = SpUtils.getString(getContext(), SpUtils.TODAY);
        if (TextUtils.isEmpty(str)) return;
        today = GsonUtils.GsonToBean(str, TodayBack.class);
        Log.d("ch","gettodayaaaa"+str);
    }

    public ScrapsBack.Data.Scraps getScrapItem(int position) {
        if (scraps == null) getScraps();
        List<ScrapsBack.Data.Scraps> list = this.scraps.getData().getScraps();
        for (ScrapsBack.Data.Scraps scrap : list) {
            if (scrap.getScrap_id() == getModel().get(position).getScrap_id()) return scrap;
        }
        return null;
    }

    public TodayBack.DataBean.ScrapsBean getTodayItem(int position) {
        if (today == null) getToday();
        List<TodayBack.DataBean.ScrapsBean> list = this.today.getData().getScraps();
        for (TodayBack.DataBean.ScrapsBean scrap : list) {
            if (scrap.getScrap_id() == getModel().get(position).getScrap_id()) return scrap;
        }
        return null;
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
        for (int i = 0; i < getModel().size(); i++) {
            double price =0;
            price = getTodayItem(i)==null?0:getTodayItem(i).getSell_price();
            all+=getModel().get(0).getQuantity()*price;
        }
        return String.format("%.2f", all);
    }

    @Override
    public void updateBySet(List<BasketBack.Data.DataBean> model) {
        getToday();
        getScraps();
        super.updateBySet(model);
    }

    @Override
    public void updateByAdd(List<BasketBack.Data.DataBean> model) {
        getToday();
        getScraps();
        super.updateByAdd(model);
    }
}
