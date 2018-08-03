package com.zony.imageloadlib.listener;

/**
 * 图片监听基类
 *
 * @param
 * @author zony
 * @time 17-9-13 下午2:18
 */
public abstract class ImgLoaderBaseListener<R> implements ImgLoaderListenerInterface<R> {

	public ImgLoaderBaseListener() {
	}

	@Override
	public void onRequestSuccess(R resource) {
		// Do nothing.
	}

	@Override
	public void onRequestFailed(Throwable throwable) {
		// Do nothing.
	}

	@Override
	public void onLoadStarted() {
		// Do nothing.
	}

	@Override
	public void onLoadFailed(R resource) {
		// Do nothing.
	}

	@Override
	public void onLoadSuccess(R resource) {
		// Do nothing.
	}

    @Override
    public void onLoadCancelled(R resource) {
        // Do nothing.
    }
}