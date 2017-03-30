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
 * Created by Administrator on 2017/3/16 0016.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> {
    private Activity activity;
    private ArrayList<String> model;

    public HomeAdapter(Activity activity, ArrayList<String> model) {
        this.activity = activity;
        this.model = new ArrayList<>();
        if (model!=null&&model.size()>0){
            this.model.addAll(model);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.adapter_home, parent, false);
        return new Holder(view);

    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tv.setText(model.get(position));
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv;
        public Holder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.adapter_tv);
        }
    }
}
