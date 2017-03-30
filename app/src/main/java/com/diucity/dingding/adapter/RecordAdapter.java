package com.diucity.dingding.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.widget.PercentCirleView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class RecordAdapter<String> extends BaseAdapter<ViewHolder> {

    public RecordAdapter(Context context, ArrayList model) {
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
}
