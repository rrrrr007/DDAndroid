package com.diucity.dingding.delegate;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;

import android.widget.EditText;
import android.widget.LinearLayout;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;



/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class LoginDelegate extends AppDelegate {
    private boolean needshow;
    private int height;

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
        setText("18244233734",R.id.edt_login_phone);
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
        if (isSoftShowing()) {
            if (needshow) return;
            zoomIn(get(R.id.iv_login_logo),get(R.id.activity_main));
        } else {
            if (!needshow) return;
            zoomOut(get(R.id.iv_login_logo),get(R.id.activity_main));
        }
    }

    private boolean isSoftShowing() {
        int screenHeight = getActivity().getWindow().getDecorView().getHeight();
        Rect rect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        height = screenHeight - rect.bottom;
        return height > screenHeight/3;
    }

    /**
     * 缩小
     * @param view
     */
    public void zoomIn(final View view,final View viewGroup) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.6f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.6f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(viewGroup, "translationY", 0.0f, -height/3);
        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();
        needshow = true;
    }

    /**
     * f放大
     * @param view
     */
    public void zoomOut(final View view,final View viewGroup) {
        view.setPivotY(0);
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.6f, 1.0f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.6f, 1.0f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(viewGroup, "translationY", view.getTranslationY(), 0);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.play(mAnimatorScaleY).with(mAnimatorTranslateY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();
        needshow = false;
    }
}
