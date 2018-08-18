package com.mysoft.university.app.utils;

import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

/**
 * View相关工具
 * <p>
 * Created by Zourw on 2018/8/12.
 */
public class ViewHelper {
    public static int sp2px(float spVal) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, metrics);
    }

    public static float px2sp(float pxVal) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (pxVal / metrics.scaledDensity);
    }

    public static void togglePassword(CheckBox checkBox, EditText editText) {
        checkBox.setOnCheckedChangeListener((button, isChecked) -> {
            if (isChecked) {
                editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            editText.setSelection(editText.length());
        });
    }

    public static int measureSize(int measureSpec, int defaultSize) {
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if (specMode == View.MeasureSpec.EXACTLY) {
            return specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            return Math.min(defaultSize, specSize);
        } else {
            return defaultSize;
        }
    }

    public static void toggleFragment(FragmentManager manager, @IdRes int containerViewId, Fragment fragment) {
        FragmentTransaction ft = manager.beginTransaction();
        List<Fragment> fragments = manager.getFragments();
        if (fragments == null || fragments.isEmpty()) {
            ft.add(containerViewId, fragment);
        } else {
            boolean contain = false;
            for (Fragment f : fragments) {
                if (f == fragment) {
                    ft.show(f);
                    contain = true;
                } else {
                    ft.hide(f);
                }
            }
            if (!contain) {
                ft.add(containerViewId, fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }
}