package com.diucity.dingding.utils;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class KeyboardUtils {
    private static boolean isFirst = true;
    public interface OnGetSoftHeightListener {
        void onShowed(int height);
    }
    public interface OnSoftKeyWordShowListener {
        void hasShow(boolean isShow);
    }

    /** * 获取软键盘的高度 * *
     @param rootView *
     @param listener
     */
    public static void getSoftKeyboardHeight(final View rootView, final OnGetSoftHeightListener listener) {
        final ViewTreeObserver.OnGlobalLayoutListener layoutListener
                = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (isFirst) {
                    final Rect rect = new Rect();
                    rootView.getWindowVisibleDisplayFrame(rect);
                    final int screenHeight = rootView.getRootView().getHeight();
                    final int heightDifference = screenHeight - rect.bottom;
//设置一个阀值来判断软键盘是否弹出
                    boolean visible = heightDifference > screenHeight / 3;
                    if (visible) {
                        isFirst = false;
                        if (listener != null) {
                            listener.onShowed(heightDifference);
                        }
                        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        };
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
    }



    /** * 判断软键盘是否弹出
     * * @param rootView
     * @param listener
     *备注：在不用的时候记得移除OnGlobalLayoutListener
     */
    public static ViewTreeObserver.OnGlobalLayoutListener doMonitorSoftKeyWord(final View rootView, final OnSoftKeyWordShowListener listener) {
        final ViewTreeObserver.OnGlobalLayoutListener layoutListener = () -> {
            final Rect rect = new Rect();
            rootView.getWindowVisibleDisplayFrame(rect);
            final int screenHeight = rootView.getRootView().getHeight();
            Log.e("TAG",rect.bottom+"#"+screenHeight);
            final int heightDifference = screenHeight - rect.bottom;
            boolean visible = heightDifference > screenHeight / 3;
            if (listener != null)
                listener.hasShow(visible);
        };
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
        return layoutListener;
    }

    public static void controlKeyboardLayout(final View root,
                                             final View editLayout) {
        // TODO Auto-generated method stub
        root.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            // TODO Auto-generated method stub
            Rect rect=new Rect();
            //获取root在窗体的可视区域
            root.getWindowVisibleDisplayFrame(rect);
            //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
            int rootInVisibleHeigh=root.getRootView().getHeight()-rect.bottom;
            //若不可视区域高度大于100，则键盘显示
            if (rootInVisibleHeigh > 100) {
                Log.v("hjb", "不可视区域高度大于100，则键盘显示");
                int[] location = new int[2];
                //获取editLayout在窗体的坐标
                editLayout.getLocationInWindow(location);
                //计算root滚动高度，使editLayout在可见区域
                int srollHeight = (location[1] + editLayout.getHeight()) - rect.bottom;
                root.scrollTo(0, srollHeight);
            } else {
                //键盘隐藏
                Log.v("hjb", "不可视区域高度小于100，则键盘隐藏");
                root.scrollTo(0, 0);
            }
        });
    }
}
