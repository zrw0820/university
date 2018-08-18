package com.mysoft.university.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.mysoft.university.di.module.ClassModule;
import com.mysoft.university.mvp.ui.fragment.ClassFragment;

import dagger.Component;

@FragmentScope
@Component(modules = ClassModule.class, dependencies = AppComponent.class)
public interface ClassComponent {
    void inject(ClassFragment fragment);
}