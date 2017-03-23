package com.diucity.dingding.activity;


import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.UserInfoDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.diucity.dingding.utils.Picassoloader;
import com.jakewharton.rxbinding.view.RxView;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class UserInfoActivity extends BaseActivity<UserInfoDelegate> {
    private Dialog head;
    private Dialog name;
    private EditText edit;
    private IHandlerCallBack iHandlerCallBack;
    private List<String> path = new ArrayList<>();
    private GalleryConfig galleryConfig;

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected Class getDelegateClass() {
        return UserInfoDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        RxView.clicks(viewDelegate.get(R.id.ll_user_head)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    showHeadDialog();
                });
        RxView.clicks(viewDelegate.get(R.id.ll_user_name)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    showNameDialog("ch");
                });
    }

    @Override
    public void initData() {
        super.initData();
        initCallBack();
        initGallery();
    }

    private void initCallBack() {
        iHandlerCallBack = new IHandlerCallBack() {
            @Override
            public void onStart() {
                Log.i(TAG, "onStart: 开启");
            }

            @Override
            public void onSuccess(List<String> photoList) {
                Log.i(TAG, "onSuccess: 返回数据");
                for (String s : photoList) {
                    Log.i(TAG, s);
                    viewDelegate.toast(s);
                }
            }

            @Override
            public void onCancel() {
                Log.i(TAG, "onCancel: 取消");
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: 结束");
            }

            @Override
            public void onError() {
                Log.i(TAG, "onError: 出错");
            }
        };

    }

    private void initGallery() {
        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new Picassoloader())
                .iHandlerCallBack(iHandlerCallBack)
                .provider("com.diucity.dingding.fileprovider")
                .pathList(path)
                .crop(true)
                .filePath("/Gallery/Pictures")
                .build();
    }

    private void showHeadDialog() {
        if (head == null) {
            View view = getLayoutInflater().inflate(R.layout.dialog_user_head, null);
            RxView.clicks(view.findViewById(R.id.dialog_tv_user_cancel))
                    .throttleFirst(2, TimeUnit.SECONDS)
                    .subscribe(aVoid -> {
                        head.dismiss();
                    });
            RxView.clicks(view.findViewById(R.id.dialog_tv_user_camera))
                    .throttleFirst(2, TimeUnit.SECONDS)
                    .subscribe(aVoid -> {
                        //相机
                        GalleryPick.getInstance().setGalleryConfig(galleryConfig).openCamera(this);
                        head.dismiss();
                    });
            RxView.clicks(view.findViewById(R.id.dialog_tv_user_photo))
                    .throttleFirst(2, TimeUnit.SECONDS)
                    .subscribe(aVoid -> {
                        //相册
                        galleryConfig.getBuilder().isOpenCamera(false).build();
                        GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(this);
                        head.dismiss();
                    });
            head = new Dialog(this, R.style.dialog);
            head.setContentView(view);
            Window dialogWindow = head.getWindow();
            dialogWindow.setGravity(Gravity.BOTTOM);
        }
        head.show();
    }

    private void showNameDialog(String str) {
        if (name == null) {
            View view = getLayoutInflater().inflate(R.layout.dialog_user_name, null);
            edit = (EditText) view.findViewById(R.id.dialog_edt_user_name);
            RxView.clicks(view.findViewById(R.id.dialog_tv_user_qrname))
                    .throttleFirst(2, TimeUnit.SECONDS)
                    .subscribe(aVoid -> {
                        viewDelegate.toast(edit.getText().toString());
                        name.dismiss();
                    });
            name = new Dialog(this, R.style.dialog1);
            name.setContentView(view);
            Window dialogWindow = name.getWindow();
            dialogWindow.setGravity(Gravity.BOTTOM);
            WindowManager m = name.getWindow().getWindowManager();
            Display d = m.getDefaultDisplay();
            WindowManager.LayoutParams p = name.getWindow().getAttributes();
            p.width = d.getWidth();
            name.getWindow().setAttributes(p);
            name.setOnShowListener(dialog -> {
                edit.requestFocus();
                InputMethodManager imm = (InputMethodManager) edit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            });
        }
        if (TextUtils.isEmpty(str))
            edit.setHint("请输入");
        else
            edit.setHint(str);
        edit.setText("");
        name.show();
    }

}
