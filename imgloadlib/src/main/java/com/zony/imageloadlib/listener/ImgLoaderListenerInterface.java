package com.zony.imageloadlib.listener;

/**
 * 监听
 *
 * @param
 * @author zony
 * @time 17-9-13 下午2:18
 */
public interface ImgLoaderListenerInterface<R> {

    /**
     * 请求成功
     *
     * @param
     * @author zony
     * @time 17-9-13 下午2:19
     */
    void onRequestSuccess(R resource);

    /**
     * 请求失败
     * 上层使用时可以使用throwable.
     * @param
     * @param throwable
     * @author zony
     * @time 17-9-13 下午2:19
     */
    void onRequestFailed(Throwable throwable);

    /**
     * 开始加载
     *
     * @param
     * @author zony
     * @time 17-9-13 下午2:19
     */
    void onLoadStarted();

    /**
     * 加载失败
     *
     * @param
     * @author zony
     * @time 17-9-13 下午2:19
     */
    void onLoadFailed(R resource);

    /**
     * 加载完成
     *
     * downloadOnly中resource返回为File
     * showImage中resource返回为Drawable
     * @param
     * @author zony
     * @time 17-9-13 下午2:19
     */
    void onLoadSuccess(R resource);

    /**
     * 加载退出
     *
     * @param
     * @author zony
     * @time 17-9-13 下午2:19
     */
    void onLoadCancelled(R resource);
}