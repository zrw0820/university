package com.mysoft.university.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.mysoft.university.mvp.contract.LearnRecordContract;
import com.mysoft.university.mvp.model.LearnRecordModel;
import com.mysoft.university.mvp.model.entity.LearnRecordInfo;
import com.mysoft.university.mvp.ui.adapter.LearnRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;


@Module
public class LearnRecordModule {
    private LearnRecordContract.View view;

    public LearnRecordModule(LearnRecordContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LearnRecordContract.View provideLearnRecordView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LearnRecordContract.Model provideLearnRecordModel(LearnRecordModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    List<LearnRecordInfo> provideRecordInfos() {
        List<LearnRecordInfo> infos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LearnRecordInfo info = new LearnRecordInfo();
            if (i % 4 == 0) {
                info.setViewType(LearnRecordInfo.DATE);
            } else {
                info.setViewType(LearnRecordInfo.ITEM);
            }
            infos.add(info);
        }
        return infos;
    }

    @ActivityScope
    @Provides
    LearnRecordAdapter provideRecordAdapter(List<LearnRecordInfo> infos) {
        return new LearnRecordAdapter(view.context(), infos);
    }
}