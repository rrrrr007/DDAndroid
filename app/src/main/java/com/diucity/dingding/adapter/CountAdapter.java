package com.diucity.dingding.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diucity.dingding.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class CountAdapter extends BaseAdapter<String> {
    private Listener listener;
    private ViewHolder mholder;
    private int index =-1;
    public CountAdapter(Context context, ArrayList<String> model) {
        super(context, model);

    }

    @Override
    public int getId() {
        return R.layout.adapter_count;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position==index){
            setEnabled(holder,false);
            mholder =holder;
        }else if(position==0&&index==-1) {
            setEnabled(holder,false);
            mholder =holder;
        }else{
            setEnabled(holder,true);
        }
        holder.itemView.setOnClickListener(v -> {
            index = position;
            if (mholder!=null){
                setEnabled(mholder,true);
            }
            mholder = holder;
           setEnabled(holder,false);
            if (listener!=null){
                listener.showEdt(position);
            }
        });
        ((TextView)holder.getView(R.id.adapter_tv_count_number)).setText("0个");

        Drawable drawable;
        String str;
        switch (position){
            case 0:
                drawable= ContextCompat.getDrawable(getContext(),R.mipmap.img_recycle_paper);
                str = "纸皮";
                break;
            case 1:
                drawable= ContextCompat.getDrawable(getContext(),R.mipmap.img_recycle_plastic);
                str = "塑料";
                break;
            case 2:
                drawable= ContextCompat.getDrawable(getContext(),R.mipmap.img_recycle_metal);
                str = "金属";
                break;
            case 3:
                drawable= ContextCompat.getDrawable(getContext(),R.mipmap.img_recycle_beer);
                str = "啤酒瓶";
                break;
            default:
                drawable= ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher);
                str = "种类";
                break;
        }
        ((ImageView)holder.getView(R.id.adapter_iv_count_src)).setImageDrawable(drawable);
        ((TextView)holder.getView(R.id.adapter_tv_count_kinds)).setText(str);
    }


    private void setEnabled(ViewHolder holder ,boolean is){
        holder.getView(R.id.adapter_tv_count_number).setEnabled(is);
        holder.getView(R.id.adapter_ll_count).setEnabled(is);
        holder.getView(R.id.adapter_tv_count_kinds).setEnabled(is);
        holder.getView(R.id.adapter_iv_count_icon).setVisibility(is? View.GONE:View.VISIBLE);
        TextView kind = holder.getView(R.id.adapter_tv_count_kinds);
        kind.getPaint().setFakeBoldText(!is);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener{
        void showEdt(int position);
    }

    public void setText(String str){
         str = TextUtils.isEmpty(str)?"0":str;
        ((TextView)mholder.getView(R.id.adapter_tv_count_number)).setText(str+"个");
    }

    public String getText(){
        String text = ((TextView) mholder.getView(R.id.adapter_tv_count_number)).getText().toString();
        String str = text.substring(0,text.length()-1);
        return str.equals("0")?"":str;
    }
}
