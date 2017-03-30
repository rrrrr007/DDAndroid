package com.diucity.dingding.adapter;

import android.content.Context;
import android.widget.TextView;

import com.diucity.dingding.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/29 0029.
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
        TextView buy = holder.getView(R.id.adapter_tv_price_buy);
        buy.setText(getModel().get(position));
    }
}
