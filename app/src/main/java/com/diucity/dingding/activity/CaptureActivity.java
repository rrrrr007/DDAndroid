package com.diucity.dingding.activity;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
                viewDelegate.startActivity(CountActivity.class);
                viewDelegate.finish();
            }

            @Override
            public void onAnalyzeFailed() {
                viewDelegate.toast("失败");
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();

        //闪光灯
        RxView.clicks(viewDelegate.get(R.id.iv_capture_flash))
                .subscribe(aVoid -> {
                    flash();
                });
        //返回
        RxView.clicks(viewDelegate.get(R.id.iv_capture_back)).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    viewDelegate.finish();
                });
    }

    private void flash(){
        if (getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FLASH)) {
        } else {
            Toast.makeText(activity, "不支持开启", Toast.LENGTH_SHORT).show();
            return;
        }
        camera = CameraManager.get().getCamera();
        parameter = camera.getParameters();

        // TODO 开灯
        if (!isOpen) {
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameter);
            isOpen = true;
            viewDelegate.setSrc(ContextCompat.getDrawable(this,R.mipmap.ic_buy_navigation_light_on),R.id.iv_capture_flash);
            Log.d("ch","kai");
        } else {  // 关灯
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameter);
            isOpen = false;
            viewDelegate.setSrc(ContextCompat.getDrawable(this,R.mipmap.ic_buy_navigation_light_off),R.id.iv_capture_flash);
            Log.d("ch","guan");
        }
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.stay,R.anim.over);
    }
}
