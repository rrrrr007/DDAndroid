package com.diucity.dingding.activity;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.diucity.dingding.R;
import com.diucity.dingding.app.App;
import com.diucity.dingding.binder.CaptureBinder;
import com.diucity.dingding.delegate.CaptureDelegate;
import com.diucity.dingding.entity.Send.SupplierBean;
import com.diucity.dingding.persent.DataBinder;
import com.jakewharton.rxbinding.view.RxView;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.camera.CameraManager;
import com.uuzuche.lib_zxing.decoding.CaptureActivityHandler;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class CaptureActivity extends BaseActivity<CaptureDelegate> {
    CaptureFragment captureFragment;
    private Camera camera;
    private Camera.Parameters parameter;
    boolean isOpen;
    private final String url = "qrcode.dinghs.com/supplier/";

    @Override
    public DataBinder getDataBinder() {
        return new CaptureBinder();
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
                int id = 0;
                viewDelegate.toast(result);
                if (viewDelegate.isSmallWarnVisiable()) {
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "当前网络不可用");
                    return;
                }

                if (result.contains(url)) {
                    int i = result.indexOf(url);
                    String s = result.substring(i + url.length(), result.length());
                    int j = s.indexOf("?");
                    if (j != -1) {
                        s = s.substring(0, j);
                    }
                    try {
                        id = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    } finally {
                        if (id == 0) {
                            viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "无效二维码");
                            restart((CaptureActivityHandler) captureFragment.getHandler());
                            return;
                        }
                        binder.work(viewDelegate, new SupplierBean(App.user.getData().getRecycler_id(), App.user.getData().getAuth_token(), id));
                    }

                } else {
                    viewDelegate.showNormalWarn(viewDelegate.get(R.id.fl_toolbar), 2, "无效二维码");
                    restart((CaptureActivityHandler) captureFragment.getHandler());
                }
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

    private void flash() {
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
            viewDelegate.setSrc(ContextCompat.getDrawable(this, R.mipmap.ic_buy_navigation_light_on), R.id.iv_capture_flash);
            Log.d("ch", "kai");
        } else {  // 关灯
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameter);
            isOpen = false;
            viewDelegate.setSrc(ContextCompat.getDrawable(this, R.mipmap.ic_buy_navigation_light_off), R.id.iv_capture_flash);
            Log.d("ch", "guan");
        }
    }

    private void restart(final CaptureActivityHandler a) {
        if (a == null) {
            return;
        }
        new Handler().postDelayed(() -> {
            Class<CaptureActivityHandler> mClass = CaptureActivityHandler.class;
            Method method = null;
            try {
                method = mClass.getDeclaredMethod("restartPreviewAndDecode");
                method.setAccessible(true);
                method.invoke(a);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, 3000);
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.stay, R.anim.over);
    }
}
