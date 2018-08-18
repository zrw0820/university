package com.mysoft.university.app.utils.prefs;

import com.cocosw.favor.AllFavor;

/**
 * 用来存储APP相关信息
 * <p>
 * Created by Zourw on 2018/8/12.
 */
@AllFavor
public interface AppPrefs {
    boolean isLogined();

    void setLogined(boolean login);

    String username();

    void setUsername(String username);

    String getToken();

    void setToken(String token);
}