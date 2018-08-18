package com.mysoft.university.mvp.model.entity;

/**
 * 学习记录信息
 * <p>
 * Created by Zourw on 2018/8/18.
 */
public class LearnRecordInfo {
    public static final int DATE = 0;
    public static final int ITEM = 1;

    private int viewType;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}