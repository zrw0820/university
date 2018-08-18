package com.mysoft.university.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.mysoft.university.app.utils.RxUtils;
import com.mysoft.university.app.utils.prefs.AppPrefs;
import com.mysoft.university.app.utils.prefs.PrefsHelper;
import com.mysoft.university.mvp.contract.LoginContract;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView) {
        super(model, rootView);
    }

    // 登录
    public void login() {
        String username = mRootView.getUsername().getInput();
        String password = mRootView.getPassword().getInput();

        mModel.login(username, password)
                .compose(RxUtils.httpResult$Load(mRootView))
                .subscribe(new ErrorHandleSubscriber<String>(mErrorHandler) {
                    @Override
                    public void onNext(String token) {
                        AppPrefs appPrefs = PrefsHelper.getAppPrefs(mApplication);
                        appPrefs.setUsername(username);
                        appPrefs.setToken(token);
                    }
                });
    }

    // 游客
    public void tourist() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
