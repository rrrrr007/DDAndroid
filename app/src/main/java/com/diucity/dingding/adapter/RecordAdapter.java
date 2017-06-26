package com.diucity.dingding.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.activity.WebActivity;
import com.diucity.dingding.entity.Back.ListBack;
import com.diucity.dingding.utils.TimeUtils;
import com.liaoinstan.springview.utils.DensityUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class RecordAdapter extends BaseAdapter<ListBack.DataBean.ItemsBean> {

    public RecordAdapter(Context context, List<ListBack.DataBean.ItemsBean> model) {
        super(context, model);
    }

    @Override
    public int getId() {
        return R.layout.adapter_record;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), WebActivity.class);
            intent.putExtra("billId", getModel().get(position).getBill_id());
            getContext().startActivity(intent);
        });
        TextView month = holder.getView(R.id.adapter_tv_record_month);
        month.setText(TimeUtils.getMonth(getModel().get(position).getTime()));
        TextView week = holder.getView(R.id.adapter_tv_record_week);
        week.setText(TimeUtils.getWeek(getModel().get(position).getTime()));
        TextView days = holder.getView(R.id.adapter_tv_record_hour);
        days.setText(TimeUtils.getHours(getModel().get(position).getTime()));
        if (position == 0) {
            month.setVisibility(View.VISIBLE);
        } else if (TimeUtils.getMonth(getModel().get(position - 1).getTime()).equals(TimeUtils.getMonth(getModel().get(position).getTime()))) {
            month.setVisibility(View.GONE);
        } else {
            month.setVisibility(View.GONE);
        }
        ImageView icon = holder.getView(R.id.adapter_iv_record_src);
        TextView moneny = holder.getView(R.id.adapter_tv_record_money);
        moneny.setText(String.format("%.2f", getModel().get(position).getAmount()));
        TextView describe = holder.getView(R.id.adapter_tv_record_describe);
        describe.setText(getModel().get(position).getExplain());
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_logo_wei_30);
        switch (getModel().get(position).getType()) {
            case 1:
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_me_money30);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_me_task30);
                break;
            case 4:
                if (getModel().get(position).getExplain().contains("北京银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank1);
                } else if (getModel().get(position).getExplain().contains("成都银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank2);
                } else if (getModel().get(position).getExplain().contains("广东发展银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank3);
                } else if (getModel().get(position).getExplain().contains("交通银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank4);
                } else if (getModel().get(position).getExplain().contains("平安银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank5);
                } else if (getModel().get(position).getExplain().contains("上海普通发展银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank6);
                } else if (getModel().get(position).getExplain().contains("兴业银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank7);
                } else if (getModel().get(position).getExplain().contains("招商银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank8);
                } else if (getModel().get(position).getExplain().contains("中国工商银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank9);
                } else if (getModel().get(position).getExplain().contains("中国光大银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank10);
                } else if (getModel().get(position).getExplain().contains("中国建设银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank11);
                } else if (getModel().get(position).getExplain().contains("中国民生银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank12);
                } else if (getModel().get(position).getExplain().contains("中国农业银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank13);
                } else if (getModel().get(position).getExplain().contains("中国人民银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank14);
                } else if (getModel().get(position).getExplain().contains("中国银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank15);
                } else if (getModel().get(position).getExplain().contains("中国邮政储蓄")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank16);
                } else if (getModel().get(position).getExplain().contains("中国实业银行")) {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank17);
                } else {
                    drawable = ContextCompat.getDrawable(getContext(), R.mipmap.bank18);
                }
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_logo_wei_30);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_logo_yi_30);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_logo_zhi_30);
                break;
            default:
        }
        icon.setImageDrawable(drawable);

        View view = holder.getView(R.id.adapter_view_record);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (position == getItemCount()-1)
            lp.leftMargin = 0;
        else
            lp.leftMargin = 30;
        view.setLayoutParams(lp);
    }

    public void adapterNotifySet(List<ListBack.DataBean.ItemsBean> model) {
        if (model != null && model.size() > 0) {
            getModel().clear();
            getModel().addAll(model);
        }
        notifyDataSetChanged();
    }

    public void adapterNotifyAdd(List<ListBack.DataBean.ItemsBean> model) {
        int i = getItemCount();
        if (model != null && model.size() > 0) {
            getModel().addAll(model);
        }
        notifyItemChanged(i);
    }
}
