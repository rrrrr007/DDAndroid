package com.diucity.dingding.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.diucity.dingding.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class ItemAdapter extends BaseAdapter<String> {
    private OnItemListener listener;
    private View ll;

    public ItemAdapter(Context context, ArrayList<String> model) {
        super(context, model);

    }

    @Override
    public int getId() {
        return R.layout.adapter_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (ll == null && position == 0) {
            ll = holder.getView(R.id.adapter_ll_item);
            ll.setBackgroundColor(getContext().getResources().getColor(R.color.selector_green));
        }
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.checked(position);
            }
        });
        TextView item = holder.getView(R.id.adapter_tv_price_item);
        if (position == getModel().size() - 1) {
            item.setText(getModel().get(position));
        } else {
            item.setText(getModel().get(position) + "/斤");
        }
    }

    public void setListener(OnItemListener listener) {
        this.listener = listener;
    }

    public void setChecked(View view) {
        ll.setBackgroundColor(getContext().getResources().getColor(R.color.selector_white));
        ll = view;
        ll.setBackgroundColor(getContext().getResources().getColor(R.color.selector_green));
    }

    public interface OnItemListener {
        void checked(int position);
    }
}
