package com.diucity.dingding.adapter;

import android.content.Context;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.entity.Back.InfoBack;
import com.diucity.dingding.entity.Back.ScrapsBack;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SpUtils;
import com.diucity.dingding.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class DialogAdapter extends BaseAdapter<InfoBack.DataBean.ScrapsBean> {
    private ScrapsBack back;

    public DialogAdapter(Context context, List<InfoBack.DataBean.ScrapsBean> model) {
        super(context, model);
        back = GsonUtils.GsonToBean(SpUtils.getString(getContext(), SpUtils.SCRAPS), ScrapsBack.class);
    }

    @Override
    public int getId() {
        return R.layout.adapter_payment;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView name = holder.getView(R.id.adapter_tv_payment_name);
        name.setText(getModel().get(position).getName());
        TextView weight = holder.getView(R.id.adapter_tv_payment_weight);
        weight.setText(StringUtils.getIntString(getModel().get(position).getQuantity()) + getModel().get(position).getUnit());
        TextView much = holder.getView(R.id.adapter_tv_payment_much);
        much.setText(StringUtils.getDoubleString(getModel().get(position).getAmount()) + "元");
    }

    private String getNameById(int id) {
        List<ScrapsBack.Data.Scraps> list = back.getData().getScraps();
        for (ScrapsBack.Data.Scraps scraps : list) {
            if (scraps.getScrap_id() == id) {
                return scraps.getName();
            }
        }
        return "";
    }

}
