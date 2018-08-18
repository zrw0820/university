package com.mysoft.university.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.mysoft.university.mvp.contract.IndexContract;
import com.mysoft.university.mvp.model.IndexModel;

import dagger.Module;
import dagger.Provides;


@Module
public class IndexModule {
    private IndexContract.View view;

    /**
     * 构建IndexModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public IndexModule(IndexContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    IndexContract.View provideIndexView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    IndexContract.Model provideIndexModel(IndexModel model) {
        return model;
    }
}