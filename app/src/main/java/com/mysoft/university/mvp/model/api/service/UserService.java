package com.mysoft.university.mvp.model.api.service;

import com.mysoft.university.app.utils.HttpResult;
import com.mysoft.university.mvp.model.body.UserLoginBody;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Zourw on 2018/8/12.
 */
public interface UserService {
    @POST("api/v1/login")
    Observable<HttpResult<String>> login(@Body UserLoginBody loginBody);
}