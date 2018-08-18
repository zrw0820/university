package com.mysoft.university.mvp.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.Preconditions;
import com.mysoft.university.BuildConfig;
import com.mysoft.university.R;
import com.mysoft.university.app.utils.ViewHelper;
import com.mysoft.university.di.component.DaggerLoginComponent;
import com.mysoft.university.di.module.CommonModule;
import com.mysoft.university.di.module.LoginModule;
import com.mysoft.university.mvp.contract.LoginContract;
import com.mysoft.university.mvp.presenter.LoginPresenter;
import com.mysoft.university.mvp.ui.view.ClearEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.username)
    ClearEditText mUsername;
    @BindView(R.id.password)
    ClearEditText mPassword;
    @BindView(R.id.toggle_pwd)
    CheckBox mTogglePwd;
    @BindView(R.id.login_btn)
    TextView mLoginBtn;

    @Inject
    Dialog loadDialog;

    @Inject
    TextWatcher mTextWatcher;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .commonModule(new CommonModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);

        mUsername.addTextChangedListener(mTextWatcher);
        mPassword.addTextChangedListener(mTextWatcher);
        ViewHelper.togglePassword(mTogglePwd, mPassword);

        if (BuildConfig.DEBUG) {
            mUsername.setInput("zourw@mysoft.com.cn");
            mPassword.setInput("Double123");
        }
    }

    @Override
    public void showLoading() {
        loadDialog.show();
    }

    @Override
    public void hideLoading() {
        loadDialog.dismiss();
    }

    @Override
    public void showMessage(@NonNull String message) {
        Preconditions.checkNotNull(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public ClearEditText getUsername() {
        return mUsername;
    }

    @Override
    public ClearEditText getPassword() {
        return mPassword;
    }

    @Override
    public TextView getLoginBtn() {
        return mLoginBtn;
    }

    @OnClick({R.id.login_btn, R.id.tourist_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                if (mPresenter != null) {
                    mPresenter.login();
                }
                break;
            case R.id.tourist_btn:
                if (mPresenter != null) {
                    mPresenter.tourist();
                }
                break;
        }
    }
}
