package com.mysoft.university.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.mysoft.university.R;
import com.mysoft.university.di.component.DaggerLearnRecordComponent;
import com.mysoft.university.di.module.LearnRecordModule;
import com.mysoft.university.mvp.contract.LearnRecordContract;
import com.mysoft.university.mvp.presenter.LearnRecordPresenter;
import com.mysoft.university.mvp.ui.adapter.LearnRecordAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 学习记录
 */
@Route(path = "/learn/record")
public class LearnRecordActivity extends BaseActivity<LearnRecordPresenter> implements LearnRecordContract.View {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    @Inject
    LearnRecordAdapter mAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLearnRecordComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .learnRecordModule(new LearnRecordModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_learn_record; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);

        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public Context context() {
        return this;
    }
}
