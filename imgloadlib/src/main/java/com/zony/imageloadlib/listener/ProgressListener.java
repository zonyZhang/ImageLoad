package com.zony.imageloadlib.listener;

/**
 * 进度回调
 *
 * @author zony
 * @time 18-8-1
 */
public interface ProgressListener {

    /**
     * 进度监听
     *
     * @param progress 进度
     * @author zony
     * @time 18-8-1
     */
    void onProgress(int progress);
}