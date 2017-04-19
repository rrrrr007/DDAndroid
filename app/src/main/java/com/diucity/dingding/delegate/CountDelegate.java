package com.diucity.dingding.delegate;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.diucity.dingding.R;
import com.diucity.dingding.adapter.CountAdapter;
import com.diucity.dingding.persent.AppDelegate;
import com.diucity.dingding.utils.ActivityUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/18 0018.
 */
public class CountDelegate extends AppDelegate {

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
        textSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#031912")), 1, text.length()-3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        setText(textSpan, R.id.tv_count_name);

        String text1 = "0.00元";
        SpannableString textSpan1 = new SpannableString(text1);
        textSpan1.setSpan(new AbsoluteSizeSpan(ActivityUtils.sp2px(getActivity(), 12)), text1.length() - 1, text1.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        setText(textSpan1, R.id.tv_count_all);
    }
}
