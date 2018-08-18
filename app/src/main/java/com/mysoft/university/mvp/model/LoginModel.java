package com.mysoft.university.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.mysoft.university.app.utils.HttpResult;
import com.mysoft.university.mvp.contract.LoginContract;
import com.mysoft.university.mvp.model.api.service.UserService;
import com.mysoft.university.mvp.model.body.UserLoginBody;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<HttpResult<String>> login(String username, String password) {
        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .login(new UserLoginBody(username, password));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}