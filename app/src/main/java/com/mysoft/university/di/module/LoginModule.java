package com.mysoft.university.di.module;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.jess.arms.di.scope.ActivityScope;
import com.mysoft.university.mvp.contract.LoginContract;
import com.mysoft.university.mvp.model.LoginModel;

import dagger.Module;
import dagger.Provides;


@Module(includes = CommonModule.class)
public class LoginModule {
    private LoginContract.View view;

    /**
     * 构建LoginModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LoginContract.View provideLoginView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LoginContract.Model provideLoginModel(LoginModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    TextWatcher provideTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView loginBtn = view.getLoginBtn();
                if (view.getUsername().isEmpty() || view.getPassword().isEmpty()) {
                    loginBtn.setEnabled(false);
                    loginBtn.setAlpha(0.45f);
                } else {
                    view.getLoginBtn().setEnabled(true);
                    loginBtn.setAlpha(1.0f);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }
}