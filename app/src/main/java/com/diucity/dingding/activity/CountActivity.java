package com.diucity.dingding.activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.EditText;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.CountAdapter;
import com.diucity.dingding.app.App;
import com.diucity.dingding.binder.CountBinder;
import com.diucity.dingding.delegate.CountDelegate;
import com.diucity.dingding.entity.Back.LoginBack;
import com.diucity.dingding.entity.Back.TodayBack;
import com.diucity.dingding.entity.Send.CreateBean;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.SpUtils;
import com.diucity.dingding.utils.StringUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CountActivity extends BaseActivity<CountDelegate> {
    private AlertDialog alertDialog;
    private AlertDialog alertDialog2;
    private boolean first = true;
    private RecyclerView rv;
    private GridLayoutManager manager;
    private CountAdapter adapter;
    private EditText edt;
    private boolean state = true;

    @Override
    public DataBinder getDataBinder() {
        return new CountBinder();
    }

    @Override
    protected Class<CountDelegate> getDelegateClass() {
        return CountDelegate.class;
    }

    @Override
    protected void bindEvenListener() {

        //Item点击
        adapter.setListener(position -> {
            smoothMoveToPosition(position);
            int id = adapter.getModel().get(position).getScrap_id();
            viewDelegate.setText((viewDelegate.spite("单价\n" + StringUtils.getDoubleString(getPriceById(id)) + adapter.getModel().get(position).getUnit())), R.id.tv_count_oneprice);
            viewDelegate.setText(adapter.getText(), R.id.edt_count);
            viewDelegate.showSoft();
            viewDelegate.setInputStyle(adapter.getModel().get(position).getUnit().equals("斤"));
        });
        //edt内容改变item内容
        RxTextView.afterTextChangeEvents(edt).subscribe(textChangeEvent -> {
            if (!first) {
                adapter.setText(textChangeEvent.editable().toString());
                viewDelegate.setVisiable(textChangeEvent.editable().toString().length() == 0, R.id.tv_count_hint);
                edt.setSelection(edt.getText().length());
                double all = getall();
                viewDelegate.setSumPrice(all);
                viewDelegate.setEnable(all > 0, R.id.btn_count_payment);
            } else
                first = false;

        });
        //结算
        RxView.clicks(viewDelegate.get(R.id.all_count_pay)).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
            showCallDialog();
        });

        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_count_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                   /* viewDelegate.startActivity(CaptureActivity.class);
                    viewDelegate.finish();*/
                    viewDelegate.showStatus(viewDelegate.get(R.id.fl_status));
                });

        viewDelegate.getRootView().addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            viewDelegate.setWidgetHeight();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        edt.requestFocus();
    }


    private void smoothMoveToPosition(int position) {
        int firstPosition = manager.findFirstVisibleItemPosition();
        int lastPosition = manager.findLastVisibleItemPosition();
        if (position <= lastPosition) {
            int top = rv.getChildAt(position - firstPosition).getTop();
            rv.smoothScrollBy(0, top);
        }
    }

    @Override
    public void initData() {
        rv = viewDelegate.get(R.id.rv_count);
        manager = (GridLayoutManager) rv.getLayoutManager();
        adapter = (CountAdapter) rv.getAdapter();
        edt = viewDelegate.get(R.id.edt_count);
        if (adapter.getModel().size() <= 0) return;
        viewDelegate.setText((viewDelegate.spite("单价\n" + StringUtils.getDoubleString(getPriceById(adapter.getModel().get(0).getScrap_id())) + adapter.getModel().get(0).getUnit())), R.id.tv_count_oneprice);
        viewDelegate.setInputStyle(adapter.getModel().get(0).getUnit().equals("斤"));
    }

    public void showCallDialog() {
        if (getall() < 1.0) {
            viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "交易总金额低于1元");
            return;
        }
        if (getall() > GsonUtils.GsonToBean(SpUtils.getString(CountActivity.this, SpUtils.USER), LoginBack.class).getData().getMoney_max()) {
            viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "交易总金额超出限制");
            return;
        }
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("是否生成订单，进行结算")
                    .setPositiveButton("结算", (dialog, which) -> {
                        alertDialog.dismiss();
                        binder.work(viewDelegate, new CreateBean(App.user.getData().getRecycler_id(), App.user.getData().getAuth_token(), getIntent().getIntExtra("value", 0), App.longitude, App.latitude, adapter.getCreate()));

                    })
                    .setNegativeButton("取消", (dialog2, which) -> alertDialog.dismiss())
                    .create();
            Window window = alertDialog.getWindow();
            window.setWindowAnimations(R.style.dialog_style);
        }
        alertDialog.show();
    }

    public void showCallDialog2(int code) {
        if (alertDialog2 == null) {
            alertDialog2 = new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setPositiveButton("确定", (dialog, which) -> {
                        alertDialog2.dismiss();

                    })
                    .create();
            Window window = alertDialog2.getWindow();
            window.setWindowAnimations(R.style.dialog_style);
        }
        alertDialog2.setMessage(code == -1 ? "回收人员回收总金额已达到上限" : "扫描的微信用户交易次数已达到上限");
        alertDialog2.show();
    }


    public double getall() {
        double a = 0;
        ArrayList<CreateBean.ScrapsBean> create = adapter.getCreate();
        for (CreateBean.ScrapsBean bean : create) {
            a += bean.getQuantity() * getPriceById(bean.getScrap_id());
        }
        return a;
    }


    private double getPriceById(int id) {
        List<TodayBack.DataBean.ScrapsBean> list = adapter.getTodayBack().getData().getScraps();
        for (TodayBack.DataBean.ScrapsBean bean : list) {
            if (bean.getScrap_id() == id) {
                return bean.getBuy_price();
            }
        }
        return 0;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus&&state){
            this.state = false;
            int status = getIntent().getIntExtra("status", 1);
            if (status == 0) {
                viewDelegate.showStatus(viewDelegate.get(R.id.fl_status));
            }
        }
    }
}
