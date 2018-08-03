package com.zony.imageloadlib.listener;

/**
 * 图片监听
 *
 * @param
 * @author zony
 * @time 17-9-13 下午2:18
 */
public abstract class ImgLoaderListener<R> extends ImgLoaderBaseListener<R> {

    public ImgLoaderListener() {
    }

    /**
     * 请求失败
     * 上层使用时可以使用throwable.
     *
     * @param
     * @param throwable
     * @author zony
     * @time 17-9-13 下午2:19
     */
    @Override
    public abstract void onRequestFailed(Throwable throwable);

    /**
     * 加载完成
     *
     * downloadOnly中resource返回为File
     * showImage中resource返回为Drawable
     * @param
     * @author zony
     * @time 17-9-13 下午2:19
     */
    @Override
    public abstract void onLoadSuccess(R resource);
}