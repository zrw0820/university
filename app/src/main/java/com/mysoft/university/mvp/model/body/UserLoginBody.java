package com.mysoft.university.mvp.model.body;

/**
 * Created by Zourw on 2018/8/12.
 */
public class UserLoginBody {
    private String username;
    private String password;

    public UserLoginBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}