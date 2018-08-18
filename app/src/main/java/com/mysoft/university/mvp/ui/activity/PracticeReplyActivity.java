package com.mysoft.university.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.mysoft.university.R;
import com.mysoft.university.di.component.DaggerPracticeReplyComponent;
import com.mysoft.university.di.module.PracticeReplyModule;
import com.mysoft.university.mvp.contract.PracticeReplyContract;
import com.mysoft.university.mvp.presenter.PracticeReplyPresenter;
import com.mysoft.university.mvp.ui.view.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 练习回复
 */
public class PracticeReplyActivity extends BaseActivity<PracticeReplyPresenter> implements PracticeReplyContract.View {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.practice_title)
    TextView mPracticeTitle;
    @BindView(R.id.practice_repay)
    EditText mPracticeRepay;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPracticeReplyComponent
                .builder()
                .appComponent(appComponent)
                .practiceReplyModule(new PracticeReplyModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_practice_reply;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
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
}
