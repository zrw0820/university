package com.mysoft.university.app.boxing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.bilibili.boxing.loader.IBoxingCrop;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.yalantis.ucrop.UCrop;

/**
 * use Ucrop(https://github.com/Yalantis/uCrop) as the implement for {@link IBoxingCrop}
 * <p>
 * Created by Zourw on 2018/8/12.
 */
public class BoxingUCrop implements IBoxingCrop {
    public static BoxingUCrop newInstance() {
        return new BoxingUCrop();
    }

    @Override
    public void onStartCrop(Context context, Fragment fragment, @NonNull BoxingCropOption cropConfig, @NonNull String path, int requestCode) {
        Uri uri = new Uri.Builder()
                .scheme("file")
                .appendPath(path)
                .build();

        UCrop.Options crop = new UCrop.Options();
        crop.setCompressionFormat(Bitmap.CompressFormat.PNG);
        crop.withMaxResultSize(cropConfig.getMaxWidth(), cropConfig.getMaxHeight());
        crop.withAspectRatio(cropConfig.getAspectRatioX(), cropConfig.getAspectRatioY());

        if (cropConfig instanceof BoxingCropOptionPlus) {
            BoxingCropOptionPlus plusCropConfig = (BoxingCropOptionPlus) cropConfig;
            crop.setCircleDimmedLayer(plusCropConfig.isCircleDimmedLayer());
            if (plusCropConfig.isCircleDimmedLayer()) {
                crop.setShowCropFrame(false);
                crop.setShowCropGrid(false);
            }
        }

        UCrop.of(uri, cropConfig.getDestination())
                .withOptions(crop)
                .start(context, fragment, requestCode);
    }

    @Override
    public Uri onCropFinish(int resultCode, Intent data) {
        if (data == null) {
            return null;
        }
        Throwable error = UCrop.getError(data);
        if (error != null) {
            return null;
        }
        return UCrop.getOutput(data);
    }
}