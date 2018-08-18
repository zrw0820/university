package com.mysoft.university.di.module;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.utils.ArmsUtils;
import com.mysoft.university.mvp.contract.RankContract;
import com.mysoft.university.mvp.model.RankModel;
import com.mysoft.university.mvp.model.entity.RankInfo;
import com.mysoft.university.mvp.ui.adapter.RankAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;


@Module
public class RankModule {
    private RankContract.View view;

    public RankModule(RankContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    RankContract.View provideRankView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    RankContract.Model provideRankModel(RankModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    List<RankInfo> provideRankInfos() {
        List<RankInfo> infos = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            infos.add(new RankInfo());
        }
        return infos;
    }

    @ActivityScope
    @Provides
    RankAdapter provideAdapter(List<RankInfo> infos) {
        return new RankAdapter(view.context(), infos);
    }

    @ActivityScope
    @Provides
    RecyclerView.ItemDecoration provideDecoration() {
        final int top = ArmsUtils.dip2px(view.context(), 12);
        final int bottom = ArmsUtils.dip2px(view.context(), 6);

        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                if (position == 0) {
                    outRect.top = top;
                }
                outRect.bottom = bottom;
            }
        };
    }
}