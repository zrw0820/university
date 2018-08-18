package com.mysoft.university.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.mysoft.university.R;
import com.mysoft.university.di.component.DaggerPracticeDetailComponent;
import com.mysoft.university.di.module.PracticeDetailModule;
import com.mysoft.university.mvp.contract.PracticeDetailContract;
import com.mysoft.university.mvp.presenter.PracticeDetailPresenter;
import com.mysoft.university.mvp.ui.adapter.PracticeAdapter;
import com.mysoft.university.mvp.ui.view.TitleBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 练习详情
 */
public class PracticeDetailActivity extends BaseActivity<PracticeDetailPresenter> implements PracticeDetailContract.View {
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;

    @BindView(R.id.practice_title)
    TextView mPracticeTitle;
    @BindView(R.id.practice_content)
    TextView mPracticeContent;
    @BindView(R.id.practice_info)
    TextView mPracticeInfo;

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    @Inject
    PracticeAdapter mAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPracticeDetailComponent
                .builder()
                .appComponent(appComponent)
                .practiceDetailModule(new PracticeDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_practice_detail;
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
