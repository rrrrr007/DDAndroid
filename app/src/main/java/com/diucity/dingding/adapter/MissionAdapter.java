package com.diucity.dingding.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.diucity.dingding.R;
import com.diucity.dingding.widget.PercentCirleView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.Holder> {
    private ArrayList<String> model;
    private Activity activity;
    private Dialog dialog;

    public MissionAdapter(Activity activity, ArrayList<String> model) {
        this.activity = activity;
        this.model = new ArrayList<>();
        if (model != null && model.size() > 0) {
            this.model.addAll(model);
        }
    }

    public void adapterNotify(ArrayList<String> model) {
        int start = getItemCount();
        if (model != null && model.size() > 0) {
            this.model.addAll(model);
        }
        notifyItemRangeInserted(start, getItemCount());


    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.adapter_mission, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.get.setVisibility(View.GONE);
        switch (position) {
            case 0:
                holder.pcv.setPercent(50);
                break;
            case 1:
                break;
            case 2:
                holder.pcv.setPercent(20);
                break;
            case 3:
                holder.pcv.setPercent(100);
                break;
            default:
                holder.pcv.setPercent(0);
        }
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(activity, position + "", Toast.LENGTH_SHORT).show();
            holder.get.setVisibility(View.VISIBLE);
            showDialog();
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView get;
        PercentCirleView pcv;

        public Holder(View itemView) {
            super(itemView);
            get = (ImageView) itemView.findViewById(R.id.adapter_tv_mission_hasGet);
            pcv = (PercentCirleView) itemView.findViewById(R.id.adapter_pcv_mission);

        }
    }

    private void showDialog() {
        if (dialog == null) {
            View view = LayoutInflater.from(activity).inflate(R.layout.dialog_mission, null);
            dialog = new Dialog(activity, R.style.dialog);
            dialog.setContentView(view);
            Window dialogWindow = dialog.getWindow();
            dialogWindow.setGravity(Gravity.CENTER);
        }
        dialog.show();
    }
}
