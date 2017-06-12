package com.diucity.dingding.delegate;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.SystemAdapter;
import com.diucity.dingding.entity.Back.SystemBack;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SpUtils;
import com.diucity.dingding.widget.ProgressView;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.List;


/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class SystemDelegate extends AppDelegate {
    private SystemAdapter adapter;
    private SpringView sv;
    private SwipeRefreshLayout srl;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_system;
    }

    @Override
    public boolean needShow() {
        return true;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        srl = get(R.id.srl_system);
        srl.setColorSchemeResources(R.color.text_green);

        sv = get(R.id.sv_system);
        sv.setFooter(new DefaultFooter(getActivity()));
        sv.setType(SpringView.Type.FOLLOW);
        sv.setEnable(false);

        adapter = new SystemAdapter(getActivity(), null);
        RecyclerView rv = get(R.id.rv_system);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);

        String s = SpUtils.getString(getActivity(), SpUtils.SYSTEM);
        if (!TextUtils.isEmpty(s)) {
            notifyDataSet(GsonUtils.GsonToBean(s, SystemBack.class).getData().getNotices());
        }

    }

    public void notifyDataAdd(List<SystemBack.DataBean.NoticesBean> list) {

        adapter.updateByAdd(list);
        if (adapter.getItemCount() > 0) {
            get(R.id.ll_system_empty).setVisibility(View.GONE);
            sv.setEnable(true);
        } else {
            get(R.id.ll_system_empty).setVisibility(View.VISIBLE);
            sv.setEnable(false);
        }
    }

    public void notifyDataSet(List<SystemBack.DataBean.NoticesBean> list) {
        adapter.updateBySet(list);
        if (adapter.getItemCount() > 0) {
            get(R.id.ll_system_empty).setVisibility(View.GONE);
            sv.setEnable(true);
        } else {
            get(R.id.ll_system_empty).setVisibility(View.VISIBLE);
            sv.setEnable(false);
        }
    }

    public void isLoading(boolean is) {
        get(R.id.progress_system).setVisibility(is ? View.VISIBLE : View.GONE);
        if (is) {
            setText("更新中...", R.id.tv_system_title);
        } else {
            setText("系统消息", R.id.tv_system_title);
        }

    }

    public void onFinishLoad() {
        if (srl.isRefreshing()) {
            srl.setRefreshing(false);
        }
        sv.onFinishFreshAndLoad();
    }

    public int getNoticeId() {
        if (adapter.getModel().size() > 0) {
            return adapter.getModel().get(adapter.getModel().size() - 1).getNotice_id();
        } else {
            return -1;
        }
    }
}
