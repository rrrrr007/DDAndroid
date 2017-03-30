package com.diucity.dingding.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.widget.Toast;

import com.diucity.dingding.R;
import com.diucity.dingding.delegate.CaptureDelegate;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.camera.CameraManager;

import java.util.concurrent.TimeUnit;

public class CaptureActivity extends BaseActivity<CaptureDelegate> {
    CaptureFragment captureFragment;
    private Camera camera;
    private Camera.Parameters parameter;
    boolean isOpen ;


    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected Class getDelegateClass() {
        return CaptureDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        captureFragment = new CaptureFragment();
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                viewDelegate.toast(result);
            }

            @Override
            public void onAnalyzeFailed() {
                viewDelegate.toast("失败");
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();

        //闪光灯
        RxView.clicks(viewDelegate.get(R.id.iv_capture_flash)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {

                    camera = CameraManager.get().getCamera();
                    parameter = camera.getParameters();
                    // TODO 开灯
                    if (isOpen) {
                        parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameter);
                        isOpen = false;
                    } else {  // 关灯
                        parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(parameter);
                        isOpen = true;
                    }
                });
    }


}
