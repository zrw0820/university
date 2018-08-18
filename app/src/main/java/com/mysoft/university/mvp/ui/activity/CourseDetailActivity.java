package com.mysoft.university.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.mysoft.university.di.component.DaggerCourseDetailComponent;
import com.mysoft.university.di.module.CourseDetailModule;
import com.mysoft.university.mvp.contract.CourseDetailContract;
import com.mysoft.university.mvp.presenter.CourseDetailPresenter;

import com.mysoft.university.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 课程详情
 */
public class CourseDetailActivity extends BaseActivity<CourseDetailPresenter> implements CourseDetailContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCourseDetailComponent
                .builder()
                .appComponent(appComponent)
                .courseDetailModule(new CourseDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_course_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}
