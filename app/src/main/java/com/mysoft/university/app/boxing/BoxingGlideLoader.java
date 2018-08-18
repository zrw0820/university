package com.mysoft.university.app.boxing;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bilibili.boxing.loader.IBoxingCallback;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.mysoft.university.R;

import java.util.Locale;

/**
 * glide as media loader
 * <p>
 * Created by Zourw on 2018/8/12.
 */
public class BoxingGlideLoader implements IBoxingMediaLoader {
    public static BoxingGlideLoader newInstance() {
        return new BoxingGlideLoader();
    }

    private static final int placeholder = R.mipmap.ic_launcher;
    private static final int error = R.mipmap.ic_launcher;

    private String wrapPath(@NonNull String absPath) {
        return String.format(Locale.CHINA, "file://%s", absPath);
    }

    @Override
    public void displayThumbnail(@NonNull ImageView img, @NonNull String absPath, int width, int height) {
        RequestOptions options = RequestOptions
                .placeholderOf(placeholder)
                .error(error)
                .centerCrop()
                .override(width, height);

        Glide.with(img)
                .load(wrapPath(absPath))
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img);
    }

    @Override
    public void displayRaw(@NonNull ImageView img, @NonNull String absPath, int width, int height, IBoxingCallback callback) {
        RequestOptions options = RequestOptions
                .placeholderOf(placeholder)
                .error(error);

        if (width > 0 && height > 0)
            options.override(width, height);

        Glide.with(img)
                .load(wrapPath(absPath))
                .apply(options)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (callback != null) {
                            callback.onFail(e);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (callback != null) {
                            callback.onSuccess();
                        }
                        return false;
                    }
                })
                .into(img);
    }
}