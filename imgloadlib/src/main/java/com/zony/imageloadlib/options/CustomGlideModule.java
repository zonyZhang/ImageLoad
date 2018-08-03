package com.zony.imageloadlib.options;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * 自定义 GlideModule
 */
@GlideModule
public class CustomGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        String cacheDir = "imageCache";
        // 250M Disk cache
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, cacheDir, 250 * 1024 * 1024));
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, cacheDir, 250 * 1024 * 1024));
//        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);

        // MemorySizeCalculator类通过考虑设备给定的可用内存和屏幕大小想出合理的默认大小.通过LruResourceCache进行缓存
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(2)
                .build();
        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);
        //okhttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // okhttp拦截器,在拦截器中监听进度
        builder.addInterceptor(new ProgressInterceptor());
        OkHttpClient okHttpClient = builder.build();
        //glide把urlConnection替换为okhttp
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(okHttpClient));
    }

    /**
     * 禁止解析Manifest文件
     * 主要针对V3升级到v4的用户，可以提升初始化速度，避免一些潜在错误。
     *
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}