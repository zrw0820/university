package com.mysoft.university.di.module;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.utils.ArmsUtils;
import com.mysoft.university.mvp.contract.CourseDetailContract;
import com.mysoft.university.mvp.model.CourseDetailModel;
import com.mysoft.university.mvp.model.entity.CourseDirectory;
import com.mysoft.university.mvp.model.entity.CourseListInfo;
import com.mysoft.university.mvp.ui.adapter.CourseDetailAdapter;
import com.mysoft.university.mvp.ui.adapter.CourseDirectoryAdapter;
import com.mysoft.university.mvp.ui.adapter.CourseTabAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;


@Module
public class CourseDetailModule {
    private CourseDetailContract.View view;

    public CourseDetailModule(CourseDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CourseDetailContract.View provideCourseDetailView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CourseDetailContract.Model provideCourseDetailModel(CourseDetailModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    RecyclerView.ItemDecoration provideTabItemDecoration() {
        final int space = ArmsUtils.dip2px(view.context(), 17);
        final int gap = ArmsUtils.dip2px(view.context(), 13);
        final int top_bottom = ArmsUtils.dip2px(view.context(), 19);

        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                if (position == 0) {
                    outRect.left = space;
                }
                if (position == parent.getChildCount() - 1) {
                    outRect.right = space;
                } else {
                    outRect.right = gap;
                }
                outRect.top = top_bottom;
                outRect.bottom = top_bottom;
            }
        };
    }

    @ActivityScope
    @Provides
    List<CourseListInfo> provideCourseListInfos() {
        List<CourseListInfo> infos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            infos.add(new CourseListInfo());
        }
        return infos;
    }

    @ActivityScope
    @Provides
    CourseTabAdapter provideCourseTabAdapter(List<CourseListInfo> infos) {
        return new CourseTabAdapter(view.context(), infos);
    }

    @ActivityScope
    @Provides
    VirtualLayoutManager provideVirtualLayoutManager() {
        return new VirtualLayoutManager(view.context());
    }

    @ActivityScope
    @Provides
    DelegateAdapter provideDelegateAdapter(VirtualLayoutManager manager) {
        return new DelegateAdapter(manager);
    }

    @ActivityScope
    @Provides
    List<CourseDirectory> provideDirectories() {
        List<CourseDirectory> directories = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            directories.add(new CourseDirectory());
        }
        return directories;
    }

    @ActivityScope
    @Provides
    CourseDirectoryAdapter provideDirectoryDirectory(List<CourseDirectory> directories) {
        return new CourseDirectoryAdapter(view.context(), directories);
    }

    @ActivityScope
    @Provides
    List<String> provideDetails() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("");
        }
        return datas;
    }

    @ActivityScope
    @Provides
    CourseDetailAdapter provideDetailAdapter(List<String> datas) {
        return new CourseDetailAdapter(view.context(), datas);
    }
}