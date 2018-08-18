package com.mysoft.university.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.mysoft.university.di.module.ClassChildModule;
import com.mysoft.university.mvp.ui.fragment.ClassChildFragment;

import dagger.Component;

@FragmentScope
@Component(modules = ClassChildModule.class, dependencies = AppComponent.class)
public interface ClassChildComponent {
    void inject(ClassChildFragment fragment);
}