package com.diucity.dingding.persent;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diucity.dingding.R;

import static android.provider.Settings.ACTION_SETTINGS;

//视图层代理的基类
public abstract class AppDelegate implements IDelegate {
    protected final SparseArray<View> mViews = new SparseArray<View>();
    private LayoutInflater inflater;
    protected View rootView;
    private Dialog dialog;
    private TextView small;
    private LayoutTransition mTransition;

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
        if (mTransition == null) {
            setupCustomAnimations(vg.getHeight());
            vg.setLayoutTransition(mTransition);

        }
        if (vg.getChildCount() > 0) {
            return;
        }
        vg.getHeight();

        View view = inflater.inflate(R.layout.view_normal, null);
        view.setOnTouchListener((v, event) -> {
            if (vg.getChildCount() > 0)
                vg.removeView(view);
            return true;
        });

        TextView tv = (TextView) view.findViewById(R.id.tv_notice_normal);
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
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv.setCompoundDrawables(drawable,null,null,null);
        vg.addView(view);
        new Handler().postDelayed(() -> {
            if (vg.getChildCount() > 0)
                vg.removeView(view);
        }, 3000);


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
        mTransition = new LayoutTransition();
        // Adding
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "translationY", -height,
                0).setDuration(
                mTransition.getDuration(LayoutTransition.APPEARING));
        mTransition.setAnimator(LayoutTransition.APPEARING, animIn);

        // 动画：DISAPPEARING
        // Removing
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "translationY", 0,
                -height).setDuration(
                mTransition.getDuration(LayoutTransition.DISAPPEARING));
        mTransition.setAnimator(LayoutTransition.DISAPPEARING, animOut);

    }
}
