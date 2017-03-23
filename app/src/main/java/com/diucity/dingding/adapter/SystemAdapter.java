package com.diucity.dingding.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diucity.dingding.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class SystemAdapter extends RecyclerView.Adapter<SystemAdapter.Holder> {
    private Activity activity;
    private ArrayList<String> model;

    public SystemAdapter(Activity activity, ArrayList<String> model) {
        this.activity = activity;
        this.model = new ArrayList<>();
        if (model!=null&&model.size()>0){
            this.model.addAll(model);
        }
    }

    public void adapterNotify(ArrayList<String> model){
        if (model!=null&&model.size()>0){
            this.model.addAll(model);
        }
        Log.d("ch",this.model.size()+"");
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.adapter_system, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView time;
        TextView content;
        public Holder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.adapter_tv_system_time);
            content = (TextView) itemView.findViewById(R.id.adapter_tv_system_content);
        }
    }
}
