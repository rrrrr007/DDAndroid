package com.diucity.dingding.delegate;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.CountAdapter;
import com.diucity.dingding.entity.Back.ScrapsBack;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.ActivityUtils;
import com.diucity.dingding.utils.GsonUtils;
import com.diucity.dingding.utils.Picassoloader;
import com.diucity.dingding.utils.SpUtils;
import com.diucity.dingding.utils.StringUtils;
import com.squareup.picasso.Picasso;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL;

/**
 * Created by Administrator on 2017/4/18 0018.
 */
public class CountDelegate extends AppDelegate {
    private int stua;
    private int height;
    private boolean needshow;
    private ScrapsBack today;
    private LayoutTransition transition;

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
        String s = SpUtils.getString(getActivity(), SpUtils.SCRAPS);
        today = GsonUtils.GsonToBean(s, ScrapsBack.class);
        RecyclerView rv = get(R.id.rv_count);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rv.setAdapter(new CountAdapter(getActivity(), today.getData().getScraps()));
        setSumPrice(0);
        setUserInfo(getActivity().getIntent().getStringExtra("name"), getActivity().getIntent().getStringExtra("url"));
        int status = getActivity().getIntent().getIntExtra("status", 1);
        if (status == 0) {
            showStatus(get(R.id.fl_status));
        }
    }

    public SpannableString spite(String str) {
        SpannableString textSpan = new SpannableString(str);
        textSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#031912")), 3, str.length() - 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return textSpan;
    }


    public void setSumPrice(double d) {
        String text = StringUtils.fmoney(d, 2) + "元";
        SpannableString textSpan1 = new SpannableString(text);
        textSpan1.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getActivity(), 12)), text.length() - 1, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        setText(textSpan1, R.id.tv_count_all);
    }

    public void setWidgetHeight() {
        if (!isSoftShowing()) {
            stua = getSoftHeight();
        }
        if (isSoftShowing() && !needshow) {
            View view = get(R.id.rl_count_edt);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
            lp.bottomMargin = height - stua;
            view.setLayoutParams(lp);
            needshow = true;
        } else if (!isSoftShowing() && needshow) {
            View view = get(R.id.rl_count_edt);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
            lp.bottomMargin = 0;
            view.setLayoutParams(lp);
            needshow = false;
        }
    }


    private boolean isSoftShowing() {
        int screenHeight = getActivity().getWindow().getDecorView().getHeight();
        Rect rect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        height = screenHeight - rect.bottom;
        return height > screenHeight / 3;
    }

    private int getSoftHeight() {
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

    public void setInputStyle(boolean isDouble) {
        EditText edt = get(R.id.edt_count);
        edt.setInputType(isDouble ? TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL : TYPE_CLASS_NUMBER);

    }

    public void setUserInfo(String name, String url) {
        String text = "和 " + name + " 交易中";
        SpannableString textSpan = new SpannableString(text);
        textSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#031912")), 1, text.length() - 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        setText(textSpan, R.id.tv_count_name);
        Picasso.with(getActivity()).load(url).resize(100, 100).transform(new Picassoloader()).placeholder(R.color.src_gray).into((ImageView) get(R.id.iv_count_header));
    }

    public void showStatus(ViewGroup vg) {
        if (transition == null) {
            setupAnimations(100);
            vg.setLayoutTransition(transition);
        }
        if (vg.getChildCount() > 0) {
            return;
        }
        vg.getHeight();
        View view = getInflater().inflate(R.layout.view_status, null);
        view.setOnTouchListener((v, event) -> {
            if (vg.getChildCount() > 0)
                vg.removeView(view);
            return true;
        });
        vg.addView(view);
    }

    private void setupAnimations(float height) {
        transition = new LayoutTransition();
        // Adding
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "translationY", -height,
                0).setDuration(
                transition.getDuration(LayoutTransition.APPEARING));
        transition.setAnimator(LayoutTransition.APPEARING, animIn);
        // Removing
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "translationY", 0,
                -height).setDuration(
                transition.getDuration(LayoutTransition.DISAPPEARING));
        transition.setAnimator(LayoutTransition.DISAPPEARING, animOut);
    }
}
