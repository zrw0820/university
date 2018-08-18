package com.mysoft.university.di.module;

import android.support.v4.app.Fragment;

import com.jess.arms.di.scope.FragmentScope;
import com.mysoft.university.mvp.contract.ClassContract;
import com.mysoft.university.mvp.model.ClassModel;
import com.mysoft.university.mvp.ui.adapter.PageAdapter;
import com.mysoft.university.mvp.ui.fragment.ClassChildFragment;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;


@Module
public class ClassModule {
    private ClassContract.View view;

    public ClassModule(ClassContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    ClassContract.View provideClassView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    ClassContract.Model provideClassModel(ClassModel model) {
        return model;
    }

    @FragmentScope
    @Provides
    List<Fragment> provideFragments() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            fragments.add(ClassChildFragment.newInstance());
        }
        return fragments;
    }

    @FragmentScope
    @Provides
    PageAdapter providePageAdapter(List<Fragment> fragments) {
        return new PageAdapter(view.getFragment().getChildFragmentManager(), fragments);
    }
}