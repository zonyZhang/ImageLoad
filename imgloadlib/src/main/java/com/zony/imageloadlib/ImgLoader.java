package com.zony.imageloadlib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ViewTarget;
import com.zony.imageloadlib.listener.ImgLoaderBaseListener;
import com.zony.imageloadlib.listener.ImgLoaderInterface;
import com.zony.imageloadlib.listener.ImgLoaderListener;
import com.zony.imageloadlib.options.ImgLoaderOptions;
import com.zony.imageloadlib.options.ImgLoaderStrategy;

import java.io.File;

/**
 * 单例模式图片加载
 *
 * @param
 * @author zony
 * @time 17-4-27 下午2:11
 */
public class ImgLoader implements ImgLoaderInterface {

    private static volatile ImgLoader instance = null;

    private ImgLoaderInterface imageLoader;

    private ImgLoader() {
        // 默认使用Glide,可替换为其他图片加载框架
        imageLoader = new ImgLoaderStrategy();
    }

    public static ImgLoader getInstance() {
        if (instance == null) {
            synchronized (ImgLoader.class) {
                if (instance == null) {
                    instance = new ImgLoader();
                }
            }
        }
        return instance;
    }

    // 可实时替换图片加载框架，默认使用Glide
    public void setImageLoader(ImgLoaderInterface loader) {
        if (loader != null) {
            imageLoader = loader;
        }
    }

    @Override
    public <T> void showImage(T model, ImageView imageView, ImgLoaderOptions options, ImgLoaderBaseListener listener) {
        imageLoader.showImage(model, imageView, options, listener);
    }

    @Override
    public <T> void downloadOnly(Context context, T model, ImgLoaderListener listener) {
        imageLoader.downloadOnly(context, model, listener);
    }

    @Override
    public <T> File getCacheFile(Context context, T imgUrl) {
        return imageLoader.getCacheFile(context, imgUrl);
    }

    /**
     * 设置tagId 解决You must not call setTag() on a view Glide is targeting
     *
     * @param
     * @author zony
     * @time 17-5-11 下午3:32
     */
    public void setTagId() {
        ViewTarget.setTagId(R.id.glide_tag);
    }

    @Override
    public void pauseAllTasks(Context context) {
        imageLoader.pauseAllTasks(context);
    }

    @Override
    public void resumeAllTasks(Context context) {
        imageLoader.resumeAllTasks(context);
    }

    @Override
    public void clearDiskCache(Context context) {
        imageLoader.clearDiskCache(context);
    }

    @Override
    public void clearAll(Context context) {
        imageLoader.clearAll(context);
    }

}