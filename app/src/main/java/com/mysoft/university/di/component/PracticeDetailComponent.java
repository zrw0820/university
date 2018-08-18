package com.mysoft.university.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.mysoft.university.di.module.PracticeDetailModule;

import com.jess.arms.di.scope.ActivityScope;
import com.mysoft.university.mvp.ui.activity.PracticeDetailActivity;

@ActivityScope
@Component(modules = PracticeDetailModule.class, dependencies = AppComponent.class)
public interface PracticeDetailComponent {
    void inject(PracticeDetailActivity activity);
}