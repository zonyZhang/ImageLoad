package com.zony.imageloadlib.listener;

import android.content.Context;
import android.widget.ImageView;

import com.zony.imageloadlib.options.ImgLoaderOptions;

import java.io.File;

/**
 * 图片加载接口
 *
 * @param
 * @author zony
 * @time 17-4-27 下午3:37
 */
public interface ImgLoaderInterface {

    /**
     * 显示图片 可加载sdcard图片 sdcard/download/test.jpg 可加载sdcard视频
     * sdcard/download/test.mp4 可加载网络图片
     * http://image.sports.baofeng.com/25a3dbb0c99c5e48e52e60941ed230be
     * 网络、本地、资源、Assets等
     *
     * @param model 网络、本地、资源、Assets路径
     * @author zony
     * @time 17-4-27 下午3:37
     */
    <T> void showImage(T model, ImageView imageView, ImgLoaderOptions options, ImgLoaderBaseListener listener);

    <T> void downloadOnly(Context context, T model, ImgLoaderListener listener);

    /**
     * 获取图片缓存文件
     *
     * @param imgUrl 图片地址
     * @author zony
     * @time 17-9-20 上午10:41
     */
    <T> File getCacheFile(Context context, T imgUrl);

    /**
     * 暂停所有正在下载或等待下载的任务
     *
     * @param
     * @author zony
     * @time 17-9-19 上午11:27
     */
    void pauseAllTasks(Context context);

    /**
     * 恢复所有任务
     *
     * @param
     * @author zony
     * @time 17-9-19 上午11:27
     */
    void resumeAllTasks(Context context);

    /**
     * 清除磁盘缓存
     *
     * @param
     * @author zony
     * @time 17-9-19 上午11:26
     */
    void clearDiskCache(Context context);

    /**
     * 清除所有缓存
     *
     * @param
     * @author zony
     * @time 17-9-19 上午11:26
     */
    void clearAll(Context context);
}