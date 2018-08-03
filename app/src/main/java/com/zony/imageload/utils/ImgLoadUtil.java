
package com.zony.imageload.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ViewTarget;
import com.zony.imageload.R;
import com.zony.imageloadlib.ImgLoader;
import com.zony.imageloadlib.options.ImgLoaderOptions;
import com.zony.imageloadlib.listener.ImgLoaderListener;
import com.zony.imageloadlib.listener.ListViewPauseOnScrollListener;
import com.zony.imageloadlib.listener.RecyclerViewPauseOnScrollListener;
import com.zony.imageloadlib.transform.GlideCircleTransform;
import com.zony.imageloadlib.transform.GlideRotateTransformation;
import com.zony.imageloadlib.transform.GlideRoundTransform;

import java.io.File;

/**
 * ImageLoaderOptions相关设置 Created by zony on 17-5-11.
 */
public class ImgLoadUtil {

    /**
     * 设置tagId 解决You must not call setTag() on a view Glide is targeting
     *
     * @param
     * @author zony
     * @time 17-5-11 下午3:32
     */
    public static void setTagId() {
        ImgLoader.getInstance().setTagId();
    }

    /**
     * 显示图片（默认配置项）
     *
     * @param
     * @author zony
     * @time 17-9-15 上午11:45
     */
    public static <T> void showImage(T url, ImageView imageView) {
        ImgLoader.getInstance().showImage(url, imageView, null, null);
    }

    /**
     * 加载图片，包含动态图判断, 如果是gif图 由于横竖占位图需要用到resId
     *
     * @param url
     * @param imageView
     * @param options
     */
    public static <T> void showImage(T url, ImageView imageView, ImgLoaderOptions options) {
        ImgLoader.getInstance().showImage(url, imageView, options, null);
    }

    /**
     * 加载图片，包含动态图判断, 如果是gif图 由于横竖占位图需要用到resId
     *
     * @param url
     * @param imageView
     * @param options
     */
    public static <T> void showImage(T url, ImageView imageView, ImgLoaderOptions options, ImgLoaderListener listener) {
        imageView.setTag(url);
        ImgLoader.getInstance().showImage(url, imageView, options, listener);
    }

    /**
     * 下载图片
     *
     * @param
     * @author zony
     * @time 17-9-15 下午2:37
     */
    public static <T> void downloadOnly(Context context, T url, ImgLoaderListener listener) {
        ImgLoader.getInstance().downloadOnly(context, url, listener);
    }

    /**
     * 获取图片缓存文件
     *
     * @param
     * @author zony
     * @time 17-9-20 上午11:13
     */
    public static <T> File getCacheFile(Context context, T url) {
        return ImgLoader.getInstance().getCacheFile(context, url);
    }

    /**
     * 清除所有缓存
     *
     * @param
     * @author zony
     * @time 17-9-20 下午1:59
     */
    public static void clearAll(Context context) {
        ImgLoader.getInstance().clearAll(context);
    }

    public static ImgLoaderOptions getOptions() {
        ImgLoaderOptions options = new ImgLoaderOptions.Builder()
                .diskCacheStrategy(ImgLoaderOptions.DiskCache.AUTOMATIC).skipMemoryCache(false).build();
        return options;
    }

    /**
     * 通用ImageLoader设置
     *
     * @param
     * @author zony
     * @time 17-5-11 下午3:31
     */
    public static ImgLoaderOptions getOptions(int defaultResId) {
        ImgLoaderOptions options = new ImgLoaderOptions.Builder().errorId(defaultResId).crossDuration(500)
                .placeholderId(defaultResId).diskCacheStrategy(ImgLoaderOptions.DiskCache.AUTOMATIC)
                .skipMemoryCache(false).build();
        return options;
    }

    /**
     * 圆角图片
     *
     * @param cornerDp
     * @return
     */
    public static ImgLoaderOptions getRoundConerOption(int defaultResId, int cornerDp) {
        ImgLoaderOptions options = new ImgLoaderOptions.Builder().errorId(defaultResId).placeholderId(defaultResId)
                .skipMemoryCache(false).isFitCenter(true).diskCacheStrategy(ImgLoaderOptions.DiskCache.AUTOMATIC)
                .transform(new GlideRoundTransform(cornerDp)).build();
        return options;
    }

    /**
     * 圆形图片
     *
     * @return
     */
    public static ImgLoaderOptions getCircleOption(int defaultResId) {
        ImgLoaderOptions options = new ImgLoaderOptions.Builder().errorId(defaultResId).placeholderId(defaultResId)
                .diskCacheStrategy(ImgLoaderOptions.DiskCache.AUTOMATIC).skipMemoryCache(false)
                .transform(new GlideCircleTransform()).skipMemoryCache(false).build();
        return options;
    }

    /**
     * 倾斜角度图片
     *
     * @return
     */
    public static ImgLoaderOptions getRotateOption(int defaultResId, float rotateRotationAngle) {
        ImgLoaderOptions options = new ImgLoaderOptions.Builder().errorId(defaultResId).placeholderId(defaultResId)
                .diskCacheStrategy(ImgLoaderOptions.DiskCache.AUTOMATIC)
                // .isFitCenter(true)
                // .override(width, height)
                .transform(new GlideRotateTransformation(rotateRotationAngle)).skipMemoryCache(false).build();
        return options;
    }

    /**
     * listview or recycleview Scroll Listener
     *
     * @param
     * @author zony
     * @time 17-9-19 上午11:34
     */
    public static ListViewPauseOnScrollListener getListViewPauseOnScrollListener() {
        ListViewPauseOnScrollListener listener = new ListViewPauseOnScrollListener(ImgLoader.getInstance(), false,
                false);
        return listener;
    }

    public static ListViewPauseOnScrollListener getListViewPauseOnScrollListener(OnScrollListener scrollListener) {
        ListViewPauseOnScrollListener listener = new ListViewPauseOnScrollListener(ImgLoader.getInstance(), false, true,
                scrollListener);
        return listener;
    }

    public static RecyclerViewPauseOnScrollListener getRecyclerViewPauseOnScrollListener() {
        RecyclerViewPauseOnScrollListener listener = new RecyclerViewPauseOnScrollListener(ImgLoader.getInstance(),
                false, true);
        return listener;
    }

    public static RecyclerViewPauseOnScrollListener getRecyclerViewPauseOnScrollListener(
            RecyclerView.OnScrollListener scrollListener) {
        RecyclerViewPauseOnScrollListener listener = new RecyclerViewPauseOnScrollListener(ImgLoader.getInstance(),
                false, true, scrollListener);
        return listener;
    }

    public static void resumeAllTasks(Context context) {
        ImgLoader.getInstance().resumeAllTasks(context);
    }

    public static void pauseAllTasks(Context context) {
        ImgLoader.getInstance().pauseAllTasks(context);
    }
}
