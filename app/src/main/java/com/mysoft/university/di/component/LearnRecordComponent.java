package com.mysoft.university.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.mysoft.university.di.module.LearnRecordModule;

import com.jess.arms.di.scope.ActivityScope;
import com.mysoft.university.mvp.ui.activity.LearnRecordActivity;

@ActivityScope
@Component(modules = LearnRecordModule.class, dependencies = AppComponent.class)
public interface LearnRecordComponent {
    void inject(LearnRecordActivity activity);
}