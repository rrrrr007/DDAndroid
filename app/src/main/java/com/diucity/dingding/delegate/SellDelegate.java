package com.diucity.dingding.delegate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.diucity.dingding.R;
import com.diucity.dingding.persent.AppDelegate;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class SellDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_sell;
    }

    @Override
    public boolean needShow() {
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        Bitmap bitmap = CodeUtils.createImage("愿天堂没有代码", 400, 400, BitmapFactory.decodeResource(getActivity().getResources(), 0));
        ((ImageView)get(R.id.iv_sell_qr)).setImageBitmap(bitmap);

    }
}
