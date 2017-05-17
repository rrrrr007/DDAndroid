package com.diucity.dingding.delegate;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.CountAdapter;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.ActivityUtils;
import com.liaoinstan.springview.utils.DensityUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/18 0018.
 */
public class CountDelegate extends AppDelegate {
    private int stua;
    private int height;
    private boolean needshow;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_count;
    }

    @Override
    public boolean needShow() {
        return true;
    }

    @Override
    public void initWidget() {
        ArrayList<String> i = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            i.add("1");
        }
        RecyclerView rv = get(R.id.rv_count);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rv.setAdapter(new CountAdapter(getActivity(), i));

        String text = "和 XXX 交易中";
        SpannableString textSpan = new SpannableString(text);
        textSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#031912")), 1, text.length() - 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        setText(textSpan, R.id.tv_count_name);

        String text1 = "0.00元";
        SpannableString textSpan1 = new SpannableString(text1);
        textSpan1.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getActivity(), 12)), text1.length() - 1, text1.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        setText(textSpan1, R.id.tv_count_all);
    }

    public void setWidgetHeight() {
        if (!isSoftShowing()){
            stua = getSoftHeight();
        }
        Log.d("ch", "g" + getSoftHeight());
        if (isSoftShowing() && !needshow) {
            View view = get(R.id.rl_count_edt);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
            lp.bottomMargin = height-stua;
            view.setLayoutParams(lp);
            Log.d("ch", "往上移");
            needshow = true;
        } else if (!isSoftShowing() && needshow) {
            View view = get(R.id.rl_count_edt);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
            lp.bottomMargin = 0;
            view.setLayoutParams(lp);
            Log.d("ch", "往下移");
            needshow = false;
        }
    }

    private int getDaoHangHeight(Context context) {
        int rid = context.getResources().getIdentifier("config_showNavigationBar","bool","android");
        if (rid != 0) {
            int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        }else {
            return 0;
        }
    }


    private boolean isSoftShowing() {
        int screenHeight = getActivity().getWindow().getDecorView().getHeight();
        Rect rect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        height = screenHeight - rect.bottom;
        return height > screenHeight / 3;
    }

    private int getSoftHeight(){
        int h;
        int screenHeight = getActivity().getWindow().getDecorView().getHeight();
        Rect rect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        h = screenHeight - rect.bottom;
        return h;
    }

    public void showSoft() {
        if (!isSoftShowing()) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
