package com.diucity.dingding.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.entity.Back.ScrapsBack;
import com.diucity.dingding.entity.Back.TodayBack;
import com.diucity.dingding.entity.Send.CreateBean;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class CountAdapter extends BaseAdapter<ScrapsBack.Data.Scraps> {
    private Listener listener;
    private ViewHolder mholder;
    private int index = -1;
    private int choice = 0;
    private TodayBack todayBack;
    private ArrayList<CreateBean.ScrapsBean> create;

    public CountAdapter(Context context, List<ScrapsBack.Data.Scraps> model) {
        super(context, model);
        todayBack = GsonUtils.GsonToBean(SpUtils.getString(getContext(), SpUtils.TODAY), TodayBack.class);
        create = new ArrayList<>();
        for (int i = 0; i < getModel().size(); i++) {
            create.add(new CreateBean.ScrapsBean(getModel().get(i).getScrap_id(), 0));
        }
    }

    @Override
    public int getId() {
        return R.layout.adapter_count;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == index) {
            setEnabled(holder, false);
            mholder = holder;
        } else if (position == 0 && index == -1) {
            setEnabled(holder, false);
            mholder = holder;
        } else {
            setEnabled(holder, true);
        }
        holder.itemView.setOnClickListener(v -> {
            choice = position;
            index = position;
            if (mholder != null) {
                setEnabled(mholder, true);
            }
            mholder = holder;
            setEnabled(holder, false);
            if (listener != null) {
                listener.showEdt(position);
            }
        });
        ((TextView) holder.getView(R.id.adapter_tv_count_number)).setText("0" + getModel().get(position).getUnit());

        Drawable drawable;

        switch (getModel().get(position).getScrap_id()) {
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.mipmap.img_recycle_paper);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.mipmap.img_recycle_plastic);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.mipmap.img_recycle_metal);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.mipmap.img_recycle_beer);
                break;
            default:
                drawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_launcher);
                break;
        }
        ((ImageView) holder.getView(R.id.adapter_iv_count_src)).setImageDrawable(drawable);
        ((TextView) holder.getView(R.id.adapter_tv_count_kinds)).setText(getModel().get(position).getName());
    }


    private void setEnabled(ViewHolder holder, boolean is) {
        holder.getView(R.id.adapter_tv_count_number).setEnabled(is);
        holder.getView(R.id.adapter_ll_count).setEnabled(is);
        holder.getView(R.id.adapter_tv_count_kinds).setEnabled(is);
        holder.getView(R.id.adapter_iv_count_icon).setVisibility(is ? View.GONE : View.VISIBLE);
        TextView kind = holder.getView(R.id.adapter_tv_count_kinds);
        kind.getPaint().setFakeBoldText(!is);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void showEdt(int position);
    }

    public void setText(String str) {
        str = TextUtils.isEmpty(str) ? "0" : str;
        ((TextView) mholder.getView(R.id.adapter_tv_count_number)).setText(str + getModel().get(choice).getUnit());
        create.get(choice).setQuantity(Double.parseDouble(str));
    }

    public String getText() {
        String text = ((TextView) mholder.getView(R.id.adapter_tv_count_number)).getText().toString();
        String str = text.substring(0, text.length() - 1);
        return str.equals("0") ? "" : str;
    }

    public TodayBack getTodayBack() {
        return todayBack;
    }

    public void setTodayBack(TodayBack todayBack) {
        this.todayBack = todayBack;
    }

    public ArrayList<CreateBean.ScrapsBean> getCreate() {
        return create;
    }

    public void setCreate(ArrayList<CreateBean.ScrapsBean> create) {
        this.create = create;
    }
}
