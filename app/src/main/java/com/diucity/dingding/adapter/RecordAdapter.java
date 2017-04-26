package com.diucity.dingding.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.diucity.dingding.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class RecordAdapter extends BaseAdapter<String> {

    public RecordAdapter(Context context, ArrayList<String> model) {
        super(context, model);
    }

    @Override
    public int getId() {
        return R.layout.adapter_record;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView month =holder.getView(R.id.adapter_tv_record_month);
        if (position==0){
            month.setVisibility(View.VISIBLE);
        }else {
            month.setVisibility(View.GONE);
        }
    }

    public void adapterNotify(ArrayList<String> model){
        int i = getItemCount();
        if (model!=null&&model.size()>0)
            getModel().addAll(model);
        notifyItemChanged(i);
    }
}
