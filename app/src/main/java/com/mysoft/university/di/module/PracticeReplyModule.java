package com.mysoft.university.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.mysoft.university.mvp.contract.PracticeReplyContract;
import com.mysoft.university.mvp.model.PracticeReplyModel;


@Module
public class PracticeReplyModule {
    private PracticeReplyContract.View view;

    /**
     * 构建PracticeReplyModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public PracticeReplyModule(PracticeReplyContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PracticeReplyContract.View providePracticeReplyView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PracticeReplyContract.Model providePracticeReplyModel(PracticeReplyModel model) {
        return model;
    }
}