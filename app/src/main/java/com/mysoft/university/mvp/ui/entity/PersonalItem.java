package com.mysoft.university.mvp.ui.entity;

import android.support.annotation.DrawableRes;

/**
 * Created by Zourw on 2018/8/18.
 */
public class PersonalItem {
    private int icon;
    private String text;
    private String summary;

    public PersonalItem(int icon, String text, String summary) {
        this.icon = icon;
        this.text = text;
        this.summary = summary;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(@DrawableRes int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
