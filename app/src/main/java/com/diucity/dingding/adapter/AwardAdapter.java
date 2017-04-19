package com.diucity.dingding.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.utils.ActivityUtils;
import com.liaoinstan.springview.utils.DensityUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class AwardAdapter extends BaseAdapter<String> {

    public AwardAdapter(Context context, ArrayList<String> model) {
        super(context, model);
    }

    @Override
    public int getId() {
        return R.layout.adapter_award;
    }

    @Override
    public int getItemViewType(int position) {
        return position > 1 ? 2 : 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return ViewHolder.get(getContext(), parent, R.layout.adapter_price);
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        View view = holder.getView(R.id.adapter_cv_price);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (position == 1)
            lp.bottomMargin = DensityUtil.dip2px(getContext(), 0);
        else
            lp.bottomMargin = DensityUtil.dip2px(getContext(), 10);
        view.setLayoutParams(lp);

        TextView tv = holder.getView(R.id.adapter_tv_price_difference);
        String text = "0.22元";
        SpannableString textSpan = new SpannableString(text);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(), 40)), 0, text.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(), 15)), text.length() - 1, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        if (position < 2) {
            holder.getView(R.id.adapter_tv_price_title).setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        } else {
            holder.getView(R.id.adapter_tv_price_title).setVisibility(position == 2 ? View.VISIBLE : View.GONE);
            textSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#606664")), text.length() - 1, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        tv.setText(textSpan);
    }
}
