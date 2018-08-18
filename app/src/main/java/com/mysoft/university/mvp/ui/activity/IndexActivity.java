package com.mysoft.university.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.mysoft.university.R;
import com.mysoft.university.app.utils.ViewHelper;
import com.mysoft.university.di.component.DaggerIndexComponent;
import com.mysoft.university.di.module.IndexModule;
import com.mysoft.university.mvp.contract.IndexContract;
import com.mysoft.university.mvp.presenter.IndexPresenter;
import com.mysoft.university.mvp.ui.fragment.ClassFragment;
import com.mysoft.university.mvp.ui.fragment.PersonalFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class IndexActivity extends BaseActivity<IndexPresenter> implements IndexContract.View {
    @BindView(R.id.radio_tab)
    RadioGroup mRadioTab;
    @BindView(R.id.radio_class)
    RadioButton mRadioClass;

    private ClassFragment mClassFragment;
    private PersonalFragment mPersonalFragment;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerIndexComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .indexModule(new IndexModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_index;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);

        mClassFragment = ClassFragment.newInstance();
        mPersonalFragment = PersonalFragment.newInstance();

        mRadioTab.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radio_class:
                    ViewHelper.toggleFragment(getSupportFragmentManager(), R.id.container, mClassFragment);
                    break;
                case R.id.radio_personal:
                    ViewHelper.toggleFragment(getSupportFragmentManager(), R.id.container, mPersonalFragment);
                    break;
                default:
                    break;
            }
        });
        mRadioClass.toggle();
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
