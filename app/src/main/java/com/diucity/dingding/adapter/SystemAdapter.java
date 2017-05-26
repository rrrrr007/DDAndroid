package com.diucity.dingding.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.activity.BaseActivity;
import com.diucity.dingding.activity.DetailActivity;
import com.diucity.dingding.entity.Back.SystemBack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class SystemAdapter extends BaseAdapter<SystemBack.DataBean.NoticesBean> {
    private String h;
    private String w;
    private String crop;

    public SystemAdapter(Context context, ArrayList<SystemBack.DataBean.NoticesBean> model) {
        super(context, model);
        WindowManager manager = ((BaseActivity) context).getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int w = outMetrics.widthPixels * 8 / 10;
        int h = w / 16 * 9;
        crop = "?x-oss-process=image/resize,m_fill,h_" + h + ",w_" + w;
        Log.d("ch", "w" + w + "h" + h + "crop" + crop);
    }

    @Override
    public int getId() {
        return R.layout.adapter_system;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TextView time = holder.getView(R.id.adapter_tv_system_time);
        String str = getModel().get(position).getSend_time();
        time.setText(str);
        holder.getView(R.id.adapter_card).setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("url", getModel().get(position).getTarget_uri());
            getContext().startActivity(intent);
        });
        TextView title = holder.getView(R.id.adapter_tv_system_title);
        title.setText(getModel().get(position).getTitle());
        if (TextUtils.isEmpty(getModel().get(position).getDescription())) {
            holder.getView(R.id.adapter_tv_system_content).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.adapter_tv_system_content).setVisibility(View.VISIBLE);
            TextView content = holder.getView(R.id.adapter_tv_system_content);
            content.setText(getModel().get(position).getDescription());
        }
        if (TextUtils.isEmpty(getModel().get(position).getCovers_uri())) {
            holder.getView(R.id.adapter_iv_system).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.adapter_iv_system).setVisibility(View.VISIBLE);
            Picasso.with(getContext()).load(getModel().get(position).getCovers_uri() + crop).placeholder(R.color.src_gray).into((ImageView) holder.getView(R.id.adapter_iv_system));
        }
        if (TextUtils.isEmpty(getModel().get(position).getTarget_uri())) {
            holder.getView(R.id.adapter_tv_system_detail).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.adapter_tv_system_detail).setVisibility(View.VISIBLE);
        }
    }


    public void adapterNotify(List<SystemBack.DataBean.NoticesBean> model) {
        if (model != null && model.size() > 0)
            getModel().addAll(model);
        notifyDataSetChanged();
    }
}
