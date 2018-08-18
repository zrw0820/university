package com.mysoft.university.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.mysoft.university.di.module.CourseDetailModule;

import com.jess.arms.di.scope.ActivityScope;
import com.mysoft.university.mvp.ui.activity.CourseDetailActivity;

@ActivityScope
@Component(modules = CourseDetailModule.class, dependencies = AppComponent.class)
public interface CourseDetailComponent {
    void inject(CourseDetailActivity activity);
}