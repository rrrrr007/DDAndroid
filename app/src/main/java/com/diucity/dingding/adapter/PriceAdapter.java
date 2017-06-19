package com.diucity.dingding.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.entity.Back.ScrapsBack;
import com.diucity.dingding.entity.Back.TodayBack;
import com.diucity.dingding.utils.ActivityUtils;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SpUtils;
import com.diucity.dingding.utils.StringUtils;
import com.liaoinstan.springview.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class PriceAdapter extends BaseAdapter<TodayBack.DataBean.ScrapsBean> {
    private ScrapsBack scraps;

    public PriceAdapter(Context context, ArrayList<TodayBack.DataBean.ScrapsBean> model) {
        super(context, model);
    }

    @Override
    public int getId() {
        return R.layout.adapter_price;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View view = holder.getView(R.id.adapter_cv_price);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (position == getItemCount() - 1) {
            lp.bottomMargin = DensityUtil.dip2px(getContext(), 100);
        } else {
            lp.bottomMargin = 0;
        }

        TextView name = holder.getView(R.id.adapter_tv_price_name);
        name.setText(nameSpan(getScrapItem(position).getName() + "/" + getScrapItem(position).getUnit()));
        TextView content = holder.getView(R.id.adapter_tv_price_content);
        content.setText("买 ￥" + StringUtils.getDoubleString(getModel().get(position).getBuy_price()) + "  卖 ￥" + StringUtils.getDoubleString(getModel().get(position).getSell_price()));
        TextView tv = holder.getView(R.id.adapter_tv_price_difference);
        tv.setText(textSpan(StringUtils.getDoubleString(getModel().get(position).getSell_price() - getModel().get(position).getBuy_price()) + "元"));

    }

    private SpannableString textSpan(String str) {
        String text = str;
        SpannableString textSpan = new SpannableString(text);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(), 40)), 0, text.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(), 15)), text.length() - 1, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return textSpan;
    }

    public void getScraps() {
        String today = SpUtils.getString(getContext(), SpUtils.SCRAPS);
        if (TextUtils.isEmpty(today)) return;
        scraps = GsonUtils.GsonToBean(today, ScrapsBack.class);
    }

    public ScrapsBack.Data.Scraps getScrapItem(int position) {
        if (scraps == null) getScraps();
        List<ScrapsBack.Data.Scraps> list = this.scraps.getData().getScraps();
        for (ScrapsBack.Data.Scraps scrap : list) {
            if (scrap.getScrap_id() == getModel().get(position).getScrap_id()) return scrap;
        }
        return null;
    }

    private SpannableString nameSpan(String str) {
        String text = str;
        SpannableString textSpan = new SpannableString(text);
        textSpan.setSpan(new StyleSpan(Typeface.BOLD), 0, text.length() - 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return textSpan;
    }


}
