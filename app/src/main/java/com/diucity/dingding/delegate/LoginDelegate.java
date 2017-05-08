package com.diucity.dingding.delegate;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.KeyboardChangeListener;
import com.liaoinstan.springview.utils.DensityUtil;


/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class LoginDelegate extends AppDelegate {
    private boolean needshow;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public boolean needShow() {
        return true;
    }

    @Override
    public void initWidget() {
        super.initWidget();

    }

    public void lineChange(View v, boolean hasFocus) {
        View view = null;
        switch (v.getId()) {
            case R.id.edt_login_phone:
                view = get(R.id.view_login_1);
                break;
            case R.id.edt_login_code:
                view = get(R.id.view_login_2);
                break;
        }
        view.setBackgroundColor(hasFocus ? Color.parseColor("#009479") : Color.parseColor("#C0CCC8"));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.height = (hasFocus ? 2 : 1);
        view.setLayoutParams(params);
    }

    public void textChange(View v, boolean has) {
        View view = null;
        switch (v.getId()) {
            case R.id.edt_login_phone:
                view = get(R.id.iv_login_icon1);
                break;
            case R.id.edt_login_code:
                view = get(R.id.iv_login_icon2);
                break;
        }
        view.setVisibility(has ? View.VISIBLE : View.GONE);
    }

    public void clearEdt(int i) {
        EditText edt = null;
        switch (i) {
            case 1:
                edt = get(R.id.edt_login_phone);
                break;
            case 2:
                edt = get(R.id.edt_login_code);
                break;
        }
        edt.setText("");
    }

    public void setWidgetHeight() {
        View view = get(R.id.iv_login_logo);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();

        if (isSoftShowing()) {
            if (needshow) return;
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.home_icon_hide);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, lp.height /= 2);
                    params.topMargin = lp.topMargin;
                    view.setLayoutParams(params);
                    needshow = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            view.startAnimation(animation);
        } else {
            if (!needshow) return;
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.home_icon_show);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, lp.height *= 2);
                    params.topMargin = lp.topMargin;
                    view.setLayoutParams(params);
                    needshow = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            view.startAnimation(animation);
        }
    }

    private boolean isSoftShowing() {
        int screenHeight = getActivity().getWindow().getDecorView().getHeight();
        Rect rect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return screenHeight - rect.bottom > screenHeight/3;
    }
}
