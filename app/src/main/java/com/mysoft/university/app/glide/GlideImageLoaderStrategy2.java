package com.mysoft.university.app.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.jess.arms.http.imageloader.BaseImageLoaderStrategy;
import com.jess.arms.http.imageloader.glide.BlurTransformation;
import com.jess.arms.http.imageloader.glide.GlideAppliesOptions;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.http.imageloader.glide.GlideRequest;
import com.jess.arms.http.imageloader.glide.GlideRequests;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 来源 GlideImageLoaderStrategy
 * <p>
 * Created by Zourw on 2018/8/12.
 */
public class GlideImageLoaderStrategy2 implements BaseImageLoaderStrategy<ImageConfigImpl2>, GlideAppliesOptions {
    @Override
    public void loadImage(Context ctx, ImageConfigImpl2 config) {
        if (ctx == null)
            throw new NullPointerException("Context is required");
        if (config == null)
            throw new NullPointerException("ImageConfigImpl2 is required");
        if (config.getImageView() == null)
            throw new NullPointerException("Imageview is required");


        GlideRequests requests;

        requests = GlideArms.with(ctx);//如果context是activity则自动使用Activity的生命周期

        GlideRequest<Drawable> glideRequest;
        if (!TextUtils.isEmpty(config.getUrl())) {
            glideRequest = requests.load(config.getUrl());
        } else if (config.getFile() != null) {
            glideRequest = requests.load(config.getFile());
        } else {
            throw new NullPointerException("load content is required");
        }

        switch (config.getCacheStrategy()) {//缓存策略
            case 0:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case 1:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case 2:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                break;
            case 3:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.DATA);
                break;
            case 4:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                break;
            default:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
        }

        if (config.isCrossFade())
            glideRequest.transition(DrawableTransitionOptions.withCrossFade());


        if (config.isCenterCrop())
            glideRequest.centerCrop();


        if (config.isCircle())
            glideRequest.circleCrop();


        if (config.isImageRadius())
            glideRequest.transform(new RoundedCorners(config.getImageRadius()));


        if (config.isBlurImage())
            glideRequest.transform(new BlurTransformation(config.getBlurValue()));


        if (config.getTransformation() != null) //glide用它来改变图形的形状
            glideRequest.transform(config.getTransformation());


        if (config.getPlaceholder() != 0)//设置占位符
            glideRequest.placeholder(config.getPlaceholder());

        if (config.getErrorPic() != 0)//设置错误的图片
            glideRequest.error(config.getErrorPic());

        if (config.getFallback() != 0)//设置请求 url 为空图片
            glideRequest.fallback(config.getFallback());


        glideRequest.into(config.getImageView());
    }

    @Override
    public void clear(final Context ctx, ImageConfigImpl2 config) {
        if (ctx == null)
            throw new NullPointerException("Context is required");
        if (config == null)
            throw new NullPointerException("ImageConfigImpl2 is required");

        if (config.getImageViews() != null && config.getImageViews().length > 0) {//取消在执行的任务并且释放资源
            for (ImageView imageView : config.getImageViews()) {
                GlideArms.get(ctx).getRequestManagerRetriever().get(ctx).clear(imageView);
            }
        }

        if (config.isClearDiskCache()) //清除本地缓存
            Observable.just(0)
                    .observeOn(Schedulers.io())
                    .subscribe(integer -> Glide.get(ctx).clearDiskCache());


        if (config.isClearMemory()) //清除内存缓存
            Observable.just(0)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(integer -> Glide.get(ctx).clearMemory());
    }


    @Override
    public void applyGlideOptions(Context context, GlideBuilder builder) {
    }
}