package com.mysoft.university.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.mysoft.university.di.module.IndexModule;

import com.jess.arms.di.scope.ActivityScope;
import com.mysoft.university.mvp.ui.activity.IndexActivity;

@ActivityScope
@Component(modules = IndexModule.class, dependencies = AppComponent.class)
public interface IndexComponent {
    void inject(IndexActivity activity);
}