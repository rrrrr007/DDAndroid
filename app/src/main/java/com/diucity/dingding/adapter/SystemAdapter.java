package com.diucity.dingding.adapter;

import android.content.Context;
import android.content.Intent;

import com.diucity.dingding.activity.DetailActivity;
import com.diucity.dingding.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class SystemAdapter extends BaseAdapter<String> {
    public SystemAdapter(Context context, ArrayList<String> model) {
        super(context, model);
    }

    @Override
    public int getId() {
        return R.layout.adapter_system;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getView(R.id.adapter_tv_system_detail).setOnClickListener(v -> {
            getContext().startActivity(new Intent(getContext(), DetailActivity.class));
        });

    }


    public void adapterNotify(ArrayList<String> model){
        if (model!=null&&model.size()>0)
            getModel().addAll(model);
        notifyDataSetChanged();
    }
}
