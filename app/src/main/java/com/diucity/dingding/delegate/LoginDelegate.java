package com.diucity.dingding.delegate;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

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
        String intent = getActivity().getIntent().getStringExtra("intent");
        if (!TextUtils.isEmpty(intent) && intent.equals("loginout")) {
            showNormalWarn(get(R.id.fl_toolbar), 1, "退出成功");
        } else if (!TextUtils.isEmpty(intent) && intent.equals("reset")) {
            showNormalWarn(get(R.id.fl_toolbar), 1, "修改密码成功,请重新登录");
        } else if (!TextUtils.isEmpty(intent) && intent.equals("haslogin")){
            showNormalWarn(get(R.id.fl_toolbar), 1, "该账号在其他设备登录,请重新登录");
        }
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
            zoomIn(get(R.id.iv_login_logo), get(R.id.activity_main));
        } else {
            if (!needshow) return;
            zoomOut(get(R.id.iv_login_logo), get(R.id.activity_main));
        }
    }

    private boolean isSoftShowing() {
        int screenHeight = getActivity().getWindow().getDecorView().getHeight();
        Rect rect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        height = screenHeight - rect.bottom;
        return height > screenHeight / 3;
    }

    /**
     * 缩小
     *
     * @param view
     */
    public void zoomIn(final View view, final View viewGroup) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.0f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.0f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(viewGroup, "translationY", 0.0f, -height / 3);
        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();
        needshow = true;
    }

    /**
     * f放大
     *
     * @param view
     */
    public void zoomOut(final View view, final View viewGroup) {
        view.setPivotY(0);
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.0f, 1.0f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.0f, 1.0f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(viewGroup, "translationY", -height / 3, 0.0f);
        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();
        needshow = false;
    }
}
