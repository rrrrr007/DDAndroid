package com.diucity.dingding.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.entity.Back.TaskBack;
import com.diucity.dingding.utils.ActivityUtils;
import com.liaoinstan.springview.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class AwardAdapter extends BaseAdapter<TaskBack.DataBean.TasksBean> {

    public AwardAdapter(Context context, ArrayList<TaskBack.DataBean.TasksBean> model) {
        super(context, model);
    }

    @Override
    public int getId() {
        return R.layout.adapter_award;
    }

    @Override
    public int getItemViewType(int position) {
        return getModel().get(position).getType();
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
            lp.bottomMargin = DensityUtil.dip2px(getContext(), 5);
        view.setLayoutParams(lp);

        holder.getView(R.id.adapter_tv_price_title).setVisibility((position == 0 || position == 2) ? View.VISIBLE : View.GONE);

        TextView title = holder.getView(R.id.adapter_tv_price_title);
        title.setText(position == 0 ? "单次交易奖励" : "累计交易奖励");

        TextView content = holder.getView(R.id.adapter_tv_price_difference);
        content.setText(textSpan(getModel().get(position).getContent()));
        TextView explain = holder.getView(R.id.adapter_tv_price_content);
        explain.setText(getModel().get(position).getExplain());
        TextView name = holder.getView(R.id.adapter_tv_price_name);
        name.setText(getModel().get(position).getName());
    }

    public SpannableString textSpan(String str) {
        String text = str;
        SpannableString textSpan = new SpannableString(text);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(), 40)), 0, text.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getContext(), 15)), text.length() - 1, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(),R.color.text_black)),text.length() - 1, text.length(),Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return textSpan;
    }
}