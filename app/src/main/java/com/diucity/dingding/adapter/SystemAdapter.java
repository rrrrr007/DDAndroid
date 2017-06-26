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
import com.diucity.dingding.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class SystemAdapter extends BaseAdapter<SystemBack.DataBean.NoticesBean> {
    private String crop;
    private boolean over;
    public SystemAdapter(Context context, List<SystemBack.DataBean.NoticesBean> model) {
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
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TextView time = holder.getView(R.id.adapter_tv_system_time);
        String send_time = getModel().get(position).getSend_time();
        if (!TextUtils.isEmpty(send_time)){
            time.setText(getTime(send_time.substring(0,send_time.length()-3)));
        }
        holder.getView(R.id.adapter_card).setOnClickListener(v -> {
            if (TextUtils.isEmpty(getModel().get(position).getTarget_uri())) return;
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


    public String getTime(String str){
        String time = TimeUtils.getTime(System.currentTimeMillis());
        String year = TimeUtils.getYear(System.currentTimeMillis());
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM");
        String yesterday = myFmt.format(System.currentTimeMillis());
        SimpleDateFormat myFmt2 = new SimpleDateFormat("dd");
        int day = Integer.parseInt(myFmt2.format(System.currentTimeMillis()));
        if (str.equals(time)) {
            return "今天";
        }else if (str.contains(yesterday)){
            if (Integer.parseInt(str.substring(8,10))+1==day){
                return "昨天";
            }else {
                return str.substring(5);
            }

        }else if (str.contains(year)){
            return str.substring(5);
        }else {
            return str;
        }

    }


}
