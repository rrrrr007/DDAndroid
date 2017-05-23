package com.diucity.dingding.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.activity.WebActivity;
import com.diucity.dingding.entity.Back.ListBack;
import com.diucity.dingding.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class RecordAdapter extends BaseAdapter<ListBack.DataBean.ItemsBean> {

    public RecordAdapter(Context context, ArrayList<ListBack.DataBean.ItemsBean> model) {
        super(context, model);
        getModel().add(new ListBack.DataBean.ItemsBean());
    }

    @Override
    public int getId() {
        return R.layout.adapter_record;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            getContext().startActivity(new Intent(getContext(), WebActivity.class));
        });
        TextView month = holder.getView(R.id.adapter_tv_record_month);
        month.setText(TimeUtils.getMonth(getModel().get(position).getTime()));
        TextView week = holder.getView(R.id.adapter_tv_record_week);
        week.setText(TimeUtils.getWeek(getModel().get(position).getTime()));
        TextView days = holder.getView(R.id.adapter_tv_record_hour);
        days.setText(TimeUtils.getHours(getModel().get(position).getTime()));
        if (position == 0) {
            month.setVisibility(View.VISIBLE);
        } else if (TimeUtils.getMonth(getModel().get(position-1).getTime()).equals(TimeUtils.getMonth(getModel().get(position).getTime()))){
            month.setVisibility(View.GONE);
        }else {
            month.setVisibility(View.GONE);
        }

        ImageView icon = holder.getView(R.id.adapter_iv_record_src);


        TextView moneny = holder.getView(R.id.adapter_tv_record_money);
        moneny.setText(String.format("%.2f", getModel().get(position).getAmount()));
        TextView describe =holder.getView(R.id.adapter_tv_record_describe);
        describe.setText(getModel().get(position).getExplain());
    }

    public void adapterNotify(List<ListBack.DataBean.ItemsBean> model) {
        int i = getItemCount();
        if (model != null && model.size() > 0)
            getModel().addAll(model);
        notifyItemChanged(i);
    }
}
