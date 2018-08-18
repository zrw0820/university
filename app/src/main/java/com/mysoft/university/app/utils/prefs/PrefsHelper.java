package com.mysoft.university.app.utils.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.cocosw.favor.FavorAdapter;

/**
 * Created by Zourw on 2018/8/12.
 */
public class PrefsHelper {
    private static final String suffix = "_prefs";

    public static SharedPreferences getSharePrefs(Context context, String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static FavorAdapter adapter(Context context, String name) {
        return new FavorAdapter
                .Builder(getSharePrefs(context, name))
                .build();
    }

    public static <T> T getPrefs(Context context, Class<T> service) {
        return adapter(context, service.getSimpleName() + suffix).create(service);
    }

    public static AppPrefs getAppPrefs(Context context) {
        return getPrefs(context, AppPrefs.class);
    }
}