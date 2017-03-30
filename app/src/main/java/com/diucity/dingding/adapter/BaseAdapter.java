package com.diucity.dingding.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<T> model;

    public BaseAdapter(Context context, ArrayList<T> model) {
        this.context = context;
        this.model = new ArrayList<>();
        if (model!=null&&model.size()>0){
            this.model.addAll(model);
        }
    }

    public abstract int getId();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.get(context,parent,getId());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public Context getContext() {
        return context;
    }

    public List<T> getModel() {
        return model;
    }
}
