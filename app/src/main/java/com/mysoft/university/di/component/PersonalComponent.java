package com.mysoft.university.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.mysoft.university.di.module.PersonalModule;

import com.jess.arms.di.scope.FragmentScope;
import com.mysoft.university.mvp.ui.fragment.PersonalFragment;

@FragmentScope
@Component(modules = PersonalModule.class, dependencies = AppComponent.class)
public interface PersonalComponent {
    void inject(PersonalFragment fragment);
}