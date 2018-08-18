package com.mysoft.university.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.mysoft.university.mvp.contract.CourseDetailContract;
import com.mysoft.university.mvp.model.CourseDetailModel;

import dagger.Module;
import dagger.Provides;


@Module
public class CourseDetailModule {
    private CourseDetailContract.View view;

    /**
     * 构建CourseDetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public CourseDetailModule(CourseDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CourseDetailContract.View provideCourseDetailView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CourseDetailContract.Model provideCourseDetailModel(CourseDetailModel model) {
        return model;
    }
}