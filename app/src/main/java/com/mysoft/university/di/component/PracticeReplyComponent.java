package com.mysoft.university.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.mysoft.university.di.module.PracticeReplyModule;

import com.jess.arms.di.scope.ActivityScope;
import com.mysoft.university.mvp.ui.activity.PracticeReplyActivity;

@ActivityScope
@Component(modules = PracticeReplyModule.class, dependencies = AppComponent.class)
public interface PracticeReplyComponent {
    void inject(PracticeReplyActivity activity);
}