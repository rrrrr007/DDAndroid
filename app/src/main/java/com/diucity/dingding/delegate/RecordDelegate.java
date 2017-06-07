package com.diucity.dingding.delegate;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.RecordAdapter;
import com.diucity.dingding.entity.Back.ListBack;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SpUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class RecordDelegate extends AppDelegate {
    private RecordAdapter adapter;
    private SpringView sv;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_record;
    }

    @Override
    public boolean needShow() {
        return true;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        sv = get(R.id.sv_record);
        sv.setHeader(new DefaultHeader(getActivity()));
        sv.setFooter(new DefaultFooter(getActivity()));
        sv.setType(SpringView.Type.FOLLOW);
        sv.setEnable(false);

        RecyclerView rv = get(R.id.rv_record);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecordAdapter(getActivity(), null);
        rv.setAdapter(adapter);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(500);
        rv.setItemAnimator(animator);

        String string = SpUtils.getString(getActivity(), SpUtils.RECORD);
        if (!TextUtils.isEmpty(string)){
            ListBack back = GsonUtils.GsonToBean(string, ListBack.class);
            notifyDataSet(back.getData().getItems());
        }

    }

    public void notifyDataSet(List<ListBack.DataBean.ItemsBean> list) {
        adapter.adapterNotifySet(list);
        if (adapter.getItemCount() > 0) {
            get(R.id.ll_record_empty).setVisibility(View.GONE);
            sv.setEnable(true);
        } else {
            get(R.id.ll_record_empty).setVisibility(View.VISIBLE);
            sv.setEnable(false);
        }
    }

    public void notifyDataAdd(List<ListBack.DataBean.ItemsBean> list) {
        adapter.adapterNotifyAdd(list);
        if (adapter.getItemCount() > 0) {
            get(R.id.ll_record_empty).setVisibility(View.GONE);
            sv.setEnable(true);
        } else {
            get(R.id.ll_record_empty).setVisibility(View.VISIBLE);
            sv.setEnable(false);
        }
    }

    public void isLoading(boolean is) {
        get(R.id.ll_record_loading).setVisibility(is ? View.VISIBLE : View.GONE);
    }

    public void onFinishLoad() {
        sv.onFinishFreshAndLoad();
    }

    public int getAdapterBillId(){
        if (adapter.getModel().size()==0){
            return -1;
        }
        return adapter.getModel().get(adapter.getModel().size()-1).getBill_id();
    }
}
