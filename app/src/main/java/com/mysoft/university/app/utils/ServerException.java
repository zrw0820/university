package com.mysoft.university.app.utils;

/**
 * 服务器异常
 * <p>
 * Created by Zourw on 2018/8/12.
 */
public class ServerException extends RuntimeException {
    public int code;
    public String message;

    public ServerException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}