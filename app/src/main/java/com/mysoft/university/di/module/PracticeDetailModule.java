package com.mysoft.university.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.mysoft.university.mvp.contract.PracticeDetailContract;
import com.mysoft.university.mvp.model.PracticeDetailModel;
import com.mysoft.university.mvp.model.entity.PracticeInfo;
import com.mysoft.university.mvp.ui.adapter.PracticeAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;


@Module
public class PracticeDetailModule {
    private PracticeDetailContract.View view;

    public PracticeDetailModule(PracticeDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PracticeDetailContract.View providePracticeDetailView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PracticeDetailContract.Model providePracticeDetailModel(PracticeDetailModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    List<PracticeInfo> providePracticeInfos() {
        List<PracticeInfo> infos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            infos.add(new PracticeInfo());
        }
        return infos;
    }

    @ActivityScope
    @Provides
    PracticeAdapter providePracticeAdapter(List<PracticeInfo> infos) {
        return new PracticeAdapter(view.context(), infos);
    }
}