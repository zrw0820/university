package com.mysoft.university.app.utils;

import com.google.gson.annotations.SerializedName;
import com.mysoft.university.mvp.model.api.Api;

/**
 * 响应数据模板
 * <p>
 * Created by Zourw on 2018/8/12.
 */
public class HttpResult<T> {
    private int code;
    @SerializedName("msg")
    private String message;
    private T data;

    public boolean success() {
        return code == Api.SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}