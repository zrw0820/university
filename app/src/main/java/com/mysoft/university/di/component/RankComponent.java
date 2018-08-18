package com.mysoft.university.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.mysoft.university.di.module.RankModule;

import com.jess.arms.di.scope.ActivityScope;
import com.mysoft.university.mvp.ui.activity.RankActivity;

@ActivityScope
@Component(modules = RankModule.class, dependencies = AppComponent.class)
public interface RankComponent {
    void inject(RankActivity activity);
}