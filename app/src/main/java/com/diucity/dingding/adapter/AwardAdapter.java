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

        TextView content = holder.getView(R.id.adapter_tv_price_difference);
        content.setText(getModel().get(position).getContent());
        TextView explain = holder.getView(R.id.adapter_tv_price_content);
        explain.setText(getModel().get(position).getExplain());
        TextView name = holder.getView(R.id.adapter_tv_price_name);
        name.setText(getModel().get(position).getName());
    }


}
