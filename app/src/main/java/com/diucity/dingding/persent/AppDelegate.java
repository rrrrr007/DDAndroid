package com.diucity.dingding.persent;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diucity.dingding.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

import static android.provider.Settings.ACTION_SETTINGS;

//视图层代理的基类
public abstract class AppDelegate implements IDelegate {
    protected final SparseArray<View> mViews = new SparseArray<View>();
    private LayoutInflater inflater;
    protected View rootView;
    private Dialog dialog;
    private View view;
    private TextView small;
    private LayoutTransition mTransition;
    private Observable<Long> observable;
    private Subscription subscribe;

    public abstract int getRootLayoutId();

    public abstract boolean needShow();

    public LayoutInflater getInflater() {
        return inflater;
    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        int rootLayoutId = getRootLayoutId();
        rootView = inflater.inflate(rootLayoutId, container, false);
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public void initWidget() {
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    public Toolbar getToolbar() {
        return null;
    }

    public <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    public <T extends View> T get(int id) {
        return (T) bindView(id);
    }

    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get(id).setOnClickListener(listener);
        }
    }

    public <T extends Activity> T getActivity() {
        return (T) rootView.getContext();
    }

    public void setSrc(Drawable src, int id) {
        ((ImageView) get(id)).setImageDrawable(src);
    }

    public void setText(String str, int id) {
        ((TextView) get(id)).setText(str);
    }

    public void setText(SpannableString textSpan, int id) {
        ((TextView) get(id)).setText(textSpan);
    }

    public String getText(int id) {
        return ((TextView) get(id)).getText().toString();
    }

    public void setVisiable(boolean is, int id) {
        get(id).setVisibility(is ? View.VISIBLE : View.GONE);
    }


    public void setEnable(boolean is, int id) {
        if (is != get(id).isEnabled())
            get(id).setEnabled(is);

    }

    public void finish() {
        getActivity().finish();
    }


    public void startActivity(Class a) {
        getActivity().startActivity(new Intent(getActivity(), a));
    }

    public void startAcitityWithAnim(Class a) {
        startActivity(a);
        getActivity().overridePendingTransition(R.anim.start, R.anim.stay);

    }

    public void startActivity(Intent intent) {
        getActivity().startActivity(intent);
    }

    public void startActivity(Class a, String key, String value) {
        Intent intent = new Intent(getActivity(), a);
        intent.putExtra(key, value);
        getActivity().startActivity(intent);
    }


    public void toast(CharSequence msg) {
        Toast.makeText(rootView.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showNormalWarn(ViewGroup vg, int pattern, String content) {
        if (vg.getChildCount() > 0) {
            return;
        }
        vg.getHeight();
        if (view == null) {
            view = inflater.inflate(R.layout.view_normal, null);
            view.setOnTouchListener((v, event) -> {
                if (subscribe != null && !subscribe.isUnsubscribed())
                    subscribe.unsubscribe();
                vg.removeView(view);
                return false;
            });
        }
        if (mTransition == null) {
            setupCustomAnimations(vg.getHeight());
        }
        vg.setLayoutTransition(mTransition);
        vg.setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(
                getActivity(), R.anim.list_animation), 0.5f));
        TextView tv = (TextView) view.findViewById(R.id.tv_notice_normal);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_notice_normal);
        Drawable drawable = null;
        switch (pattern) {
            case 1:
                tv.setTextColor(Color.parseColor("#F5F8F7"));
                drawable = ContextCompat.getDrawable(getActivity(), R.mipmap.ic_cmm_success_small_white);
                view.setBackgroundResource(R.drawable.normal_color1);
                break;
            case 2:
                tv.setTextColor(Color.parseColor("#031912"));
                drawable = ContextCompat.getDrawable(getActivity(), R.mipmap.ic_cmm_remind_small);
                view.setBackgroundResource(R.drawable.normal_color2);

                break;
            case 3:
                tv.setTextColor(Color.parseColor("#031912"));
                drawable = ContextCompat.getDrawable(getActivity(), R.mipmap.ic_cmm_error_small);
                view.setBackgroundResource(R.drawable.normal_color3);
                break;
        }
        tv.getPaint().setFakeBoldText(true);
        tv.setText(content);
        iv.setImageDrawable(drawable);
        vg.addView(view);
        if (observable == null)
            observable = Observable.timer(3, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread());
        subscribe = observable.subscribe(aLong -> {
            vg.removeView(view);
        });
    }

    public void showSmallWarn() {
        if (!needShow()) {
            return;
        }
        ViewGroup vg = get(R.id.notice);
        if (vg.getChildCount() > 0) {
            return;
        }
        if (small == null) {
            small = (TextView) inflater.inflate(R.layout.view_small, null);
            small.setOnClickListener(v -> {
                getActivity().startActivity(new Intent(ACTION_SETTINGS));
            });
        }
        vg.addView(small);
    }

    public boolean isSmallWarnVisiable() {
        ViewGroup vg = get(R.id.notice);
        return vg.getChildCount() > 0;
    }

    public void hideSmallWarn() {
        if (!needShow()) {
            return;
        }
        ViewGroup vg = get(R.id.notice);
        if (small != null) {
            vg.removeView(small);
        }

    }

    public void showLoadingWarn(String str) {
        if (dialog == null) {
            View view = inflater.inflate(R.layout.dialog_login, null);

            dialog = new Dialog(rootView.getContext(), R.style.dialog);
            dialog.setContentView(view);
            dialog.setCancelable(false);
            Window dialogWindow = dialog.getWindow();
            dialogWindow.setGravity(Gravity.CENTER);
        }
        if (!TextUtils.isEmpty(str)) {
            TextView text = (TextView) dialog.findViewById(R.id.tv_dialog_text);
            text.setText(str);
        }
        dialog.show();
    }

    public void hideLoadingWarn() {
        if (dialog != null)
            dialog.dismiss();
    }

    private void setupCustomAnimations(float height) {
        // Changing while Adding
        mTransition = new LayoutTransition();
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 1);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 1);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);

        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX,
                pvhScaleY).setDuration(
                mTransition.getDuration(LayoutTransition.CHANGE_APPEARING));
        mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);
        changeIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                // View也支持此种动画执行方式了
                view.setScaleX(1f);
                view.setScaleY(1f);
            }
        });
        // 动画：CHANGE_DISAPPEARING
        // Changing while Removing
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.9999f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe(
                "rotation", kf0, kf1, kf2);
        final ObjectAnimator changeOut = ObjectAnimator
                .ofPropertyValuesHolder(this, pvhLeft, pvhTop, pvhRight,
                        pvhBottom, pvhRotation)
                .setDuration(
                        mTransition
                                .getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        mTransition
                .setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeOut);
        changeOut.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotation(0f);
            }
        });

        // 动画：APPEARING
        // Adding
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "translationY", -height,
                0).setDuration(
                mTransition.getDuration(LayoutTransition.APPEARING));
        mTransition.setAnimator(LayoutTransition.APPEARING, animIn);
        animIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationY(0f);
            }
        });

        // 动画：DISAPPEARING
        // Removing
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "translationY", 0,
                -height).setDuration(
                mTransition.getDuration(LayoutTransition.DISAPPEARING));
        mTransition.setAnimator(LayoutTransition.DISAPPEARING, animOut);
        animOut.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationX(0f);
            }
        });
    }
}
