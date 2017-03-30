package com.diucity.dingding.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diucity.dingding.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class ViewPager2Adapter extends PagerAdapter {
    private Listener listener;
    private ArrayList<View> model;
    private Context context;
    private ArrayList<String> list;
    private View ll;

    public ViewPager2Adapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.model = new ArrayList<>();
        this.list = new ArrayList<>();
        if (list != null && list.size() > 0) {
            this.list.addAll(list);
            for (int i = 0; i < this.list.size(); i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.adapter_item, null);
                TextView buy = (TextView) view.findViewById(R.id.adapter_tv_price_item);
                buy.setText(list.get(i));
                if (i == 0) {
                    ll = view.findViewById(R.id.adapter_ll_item);
                    ll.setBackgroundColor(context.getResources().getColor(R.color.selector_green));
                }
                this.model.add(view);
                int finalI = i;
                view.setOnClickListener(v -> {
                    if (listener != null)
                        listener.checked(finalI);
                });
            }
        }
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(model.get(position));
        return model.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(model.get(position));
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void checked(int position);
    }

    public void checked(int position){
        ll.setBackgroundColor(context.getResources().getColor(R.color.selector_white));
        ll = model.get(position).findViewById(R.id.adapter_ll_item);
        ll.setBackgroundColor(context.getResources().getColor(R.color.selector_green));
    }

}
