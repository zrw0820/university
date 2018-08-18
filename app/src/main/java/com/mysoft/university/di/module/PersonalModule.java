package com.mysoft.university.di.module;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.jess.arms.di.scope.FragmentScope;
import com.mysoft.university.R;
import com.mysoft.university.mvp.contract.PersonalContract;
import com.mysoft.university.mvp.model.PersonalModel;
import com.mysoft.university.mvp.ui.adapter.PersonalAdapter;
import com.mysoft.university.mvp.ui.adapter.PersonalHeaderAdapter;
import com.mysoft.university.mvp.ui.entity.PersonalItem;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;


@Module
public class PersonalModule {
    private PersonalContract.View view;

    public PersonalModule(PersonalContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    PersonalContract.View providePersonalView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    PersonalContract.Model providePersonalModel(PersonalModel model) {
        return model;
    }

    @FragmentScope
    @Provides
    VirtualLayoutManager provideVirtualLayoutManager() {
        return new VirtualLayoutManager(view.context());
    }

    @FragmentScope
    @Provides
    DelegateAdapter provideDelegateAdapter(VirtualLayoutManager manager) {
        return new DelegateAdapter(manager);
    }

    @FragmentScope
    @Provides
    PersonalHeaderAdapter provideHeaderAdapter() {
        return new PersonalHeaderAdapter(view.context());
    }

    @FragmentScope
    @Provides
    List<PersonalItem> providePersonalItems() {
        List<PersonalItem> items = new ArrayList<>();
        items.add(new PersonalItem(R.drawable.icon_record, "学习记录", ""));
        items.add(new PersonalItem(R.drawable.icon_week, "本周学习时长", "250小时20分"));
        items.add(new PersonalItem(R.drawable.icon_total, "累计学习时长", "88小时34分"));
        items.add(new PersonalItem(R.drawable.icon_help, "帮助与反馈", ""));
        items.add(new PersonalItem(R.drawable.icon_setting, "设置", ""));
        return items;
    }

    @FragmentScope
    @Provides
    PersonalAdapter providePersonalAdapter(List<PersonalItem> items) {
        return new PersonalAdapter(view.context(), items);
    }
}