package com.mysoft.university.mvp.ui.listener;

/**
 * RecycleView ItemClick
 * <p>
 * Created by Zourw on 2018/8/20.
 */
public interface OnItemClickListener<T> {
    void onItemClick(int position, T t);
}
