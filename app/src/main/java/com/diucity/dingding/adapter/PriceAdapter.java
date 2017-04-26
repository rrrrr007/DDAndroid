package com.diucity.dingding.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.utils.ActivityUtils;
import com.liaoinstan.springview.utils.DensityUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class PriceAdapter extends BaseAdapter<String> {

    public PriceAdapter(Context context, ArrayList<String> model) {
        super(context, model);
    }

    @Override
    public int getId() {
        return R.layout.adapter_price;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getView(R.id.adapter_tv_price_title).setVisibility(position==0?View.VISIBLE:View.GONE);
        TextView tv = holder.getView(R.id.adapter_tv_price_difference);
        String text = "0.22元";
        SpannableString textSpan = new SpannableString(text);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(),40)),0,text.length()-1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(),15)),text.length()-1,text.length(),Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tv.setText(textSpan);
    }


}
