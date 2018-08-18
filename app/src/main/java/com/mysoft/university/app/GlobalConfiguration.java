package com.mysoft.university.app;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.http.log.RequestInterceptor;
import com.jess.arms.integration.ConfigModule;
import com.mysoft.university.BuildConfig;
import com.mysoft.university.app.glide.GlideImageLoaderStrategy2;
import com.mysoft.university.app.utils.Constants;
import com.mysoft.university.app.utils.SSLHelper;
import com.mysoft.university.mvp.model.api.Api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * ================================================
 * App 的全局配置信息在此配置, 需要将此实现类声明到 AndroidManifest 中
 * ConfigModule 的实现类可以有无数多个, 在 Application 中只是注册回调, 并不会影响性能 (多个 ConfigModule 在多 Module 环境下尤为受用)
 *
 * @see com.jess.arms.base.delegate.AppDelegate
 * @see com.jess.arms.integration.ManifestParser
 * Created by MVPArmsTemplate
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public final class GlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        if (!BuildConfig.LOG_DEBUG) {
            builder.printHttpLogLevel(RequestInterceptor.Level.NONE);
        }


        builder.baseurl(Api.BASE_URL)
                .imageLoaderStrategy(new GlideImageLoaderStrategy2())
                .globalHttpHandler(new GlobalHttpHandlerImpl(context))
                .responseErrorListener(new ResponseErrorListenerImpl())
                .gsonConfiguration((c, b) -> b
                        .serializeNulls()
                        .enableComplexMapKeySerialization())
                .okhttpConfiguration((c, b) -> {
                    SSLHelper.SSLParams sslParams = SSLHelper.generate(null, null, null);
                    b.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
                    b.connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
                            .readTimeout(Constants.TIME_OUT, TimeUnit.SECONDS);
                    ProgressManager.getInstance().with(b);
                    RetrofitUrlManager.getInstance().with(b);
                })
                .rxCacheConfiguration((context1, rxCacheBuilder) -> {
                    rxCacheBuilder.useExpiredDataIfLoaderNotAvailable(true);
                    return null;
                });
    }

    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {
        lifecycles.add(new AppLifecyclesImpl());
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        lifecycles.add(new ActivityLifecycleCallbacksImpl());
    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
    }
}
