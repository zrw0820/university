package com.mysoft.university.app.utils;

import com.jess.arms.mvp.IView;
import com.jess.arms.utils.RxLifecycleUtils;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Zourw on 2018/8/12.
 */
public class RxUtils {
    public static <T> ObservableTransformer<HttpResult<T>, T> httpResult() {
        return upstream -> upstream.map((Function<HttpResult<T>, T>) result -> {
            if (result.success()) {
                return result.getData();
            }
            throw new ServerException(result.getCode(), result.getMessage());
        });
    }

    public static <T> ObservableTransformer<HttpResult<T>, T> httpResult(IView view) {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.httpResult())
                .compose(RxLifecycleUtils.bindToLifecycle(view));
    }

    public static <T> ObservableTransformer<HttpResult<T>, T> httpResult$Load(IView view) {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.showLoading())
                .compose(RxUtils.httpResult())
                .doFinally(view::hideLoading)
                .compose(RxLifecycleUtils.bindToLifecycle(view));
    }
}
