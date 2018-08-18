package com.mysoft.university.di.module;

import com.jess.arms.di.scope.FragmentScope;
import com.mysoft.university.mvp.contract.ClassChildContract;
import com.mysoft.university.mvp.model.ClassChildModel;
import com.mysoft.university.mvp.model.entity.ClassInfo;
import com.mysoft.university.mvp.ui.adapter.IndexClassAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;


@Module
public class ClassChildModule {
    private ClassChildContract.View view;

    public ClassChildModule(ClassChildContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    ClassChildContract.View provideClassChildView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    ClassChildContract.Model provideClassChildModel(ClassChildModel model) {
        return model;
    }

    @FragmentScope
    @Provides
    List<ClassInfo> provideClassInfos() {
        List<ClassInfo> infos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            infos.add(new ClassInfo());
        }
        return infos;
    }

    @FragmentScope
    @Provides
    IndexClassAdapter provideClassAdapter(List<ClassInfo> infos) {
        return new IndexClassAdapter(view.context(), infos);
    }
}