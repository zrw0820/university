package com.mysoft.university.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.mysoft.university.R;
import com.mysoft.university.di.component.DaggerRankComponent;
import com.mysoft.university.di.module.RankModule;
import com.mysoft.university.mvp.contract.RankContract;
import com.mysoft.university.mvp.presenter.RankPresenter;
import com.mysoft.university.mvp.ui.adapter.RankAdapter;
import com.mysoft.university.mvp.ui.view.TitleBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 排行榜
 */
public class RankActivity extends BaseActivity<RankPresenter> implements RankContract.View {
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;

    @BindView(R.id.serial_no)
    TextView mSerialNo;
    @BindView(R.id.portrait)
    ImageView mPortrait;
    @BindView(R.id.username)
    TextView mUsername;
    @BindView(R.id.time)
    TextView mTime;

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    @Inject
    RecyclerView.ItemDecoration mItemDecoration;

    @Inject
    RankAdapter mAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerRankComponent
                .builder()
                .appComponent(appComponent)
                .rankModule(new RankModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_rank;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
        mRecycleView.addItemDecoration(mItemDecoration);
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
