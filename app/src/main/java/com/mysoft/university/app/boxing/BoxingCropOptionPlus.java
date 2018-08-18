package com.mysoft.university.app.boxing;

import android.net.Uri;

import com.bilibili.boxing.model.config.BoxingCropOption;

/**
 * 为 BoxingCropOption 增加属性
 * <p>
 * Created by Zourw on 2018/8/12.
 */
public class BoxingCropOptionPlus extends BoxingCropOption {
    private boolean isCircleDimmedLayer;

    public BoxingCropOptionPlus(Uri destination) {
        super(destination);
    }

    public boolean isCircleDimmedLayer() {
        return isCircleDimmedLayer;
    }

    public void setCircleDimmedLayer(boolean circleDimmedLayer) {
        isCircleDimmedLayer = circleDimmedLayer;
    }
}