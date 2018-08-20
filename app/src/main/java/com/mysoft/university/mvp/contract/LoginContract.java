package com.mysoft.university.mvp.contract;

import android.widget.TextView;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.mysoft.university.app.utils.HttpResult;
import com.mysoft.university.mvp.ui.view.ClearEditText;

import io.reactivex.Observable;


public interface LoginContract {
    interface View extends IView {
        ClearEditText getUsername();

        ClearEditText getPassword();

        TextView getLoginBtn();

        void toIndexActivity();
    }

    interface Model extends IModel {
        Observable<HttpResult<String>> login(String username, String password);
    }
}