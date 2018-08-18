package com.mysoft.university.app;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.PermissionUtil;
import com.mysoft.university.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import timber.log.Timber;

/**
 * ================================================
 * 展示 {@link Application.ActivityLifecycleCallbacks} 的用法
 * <p>
 * Created by MVPArmsTemplate
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Timber.d(activity + " - onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Timber.d(activity + " - onActivityStarted");

        View backBtn = activity.findViewById(R.id.back_btn);
        if (backBtn != null) {
            backBtn.setOnClickListener(view -> activity.onBackPressed());
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Timber.d(activity + " - onActivityResumed");

        if (activity instanceof BoxingActivity) {
            PermissionUtil.requestPermission(
                    new PermissionUtil.RequestPermission() {
                        @Override
                        public void onRequestPermissionSuccess() {

                        }

                        @Override
                        public void onRequestPermissionFailure(List<String> permissions) {
                            new AlertDialog.Builder(activity)
                                    .setMessage("获取相机权限")
                                    .setNegativeButton("否", (dialog, which) -> activity.finish())
                                    .create()
                                    .show();
                        }

                        @Override
                        public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {

                        }
                    }, new RxPermissions(activity), ArmsUtils.obtainAppComponentFromContext(activity).rxErrorHandler(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
            );
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Timber.d(activity + " - onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Timber.d(activity + " - onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Timber.d(activity + " - onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Timber.d(activity + " - onActivityDestroyed");
        // 横竖屏切换或配置改变时, Activity 会被重新创建实例, 但 Bundle 中的基础数据会被保存下来,移除该数据是为了保证重新创建的实例可以正常工作
        // 可以使用activity.getIntent().remove()...
    }
}
