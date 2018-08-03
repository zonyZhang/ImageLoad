package com.zony.imageloadlib.options;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zony.imageloadlib.listener.ImgLoaderBaseListener;

/**
 * Target就是使用Glide获取到资源之后资源作用的目标， 我们通常是用Glide加载完资源后显示到ImageView中，这个ImageView就是目标
 *
 * @param
 * @author zony
 * @time 17-4-27 下午3:37
 */
public class ImgTarget<Z> extends ImageViewTarget<Z> {

	private static final String TAG = "ImgTarget";

	private ImgLoaderBaseListener mImgLoaderListener;

	private ImageView mImageView;

	// 初始时mImageView显示的状态
	private int visibleInit;

	private boolean isGoneInit;

	// 是否加载完成，成功或失败
	private boolean isLoadComplete = false;

	public ImgTarget(ImageView view) {
		super(view);
		initImagViewTarget(view);
	}

	public ImgTarget(ImageView view, ImgLoaderBaseListener listener) {
		super(view);
		mImgLoaderListener = listener;
		initImagViewTarget(view);
	}

	private void initImagViewTarget(ImageView view) {
		mImageView = view;
		visibleInit = mImageView.getVisibility();
		if (mImageView.getVisibility() == View.VISIBLE) {
			isGoneInit = false;
		} else {
			isGoneInit = true;
		}
	}

	@Override
	protected void setResource(@Nullable Z resource) {
		setImageDrawable(resource);
	}

	@Override
	public void onLoadStarted(@Nullable Drawable placeholder) {
		super.onLoadStarted(placeholder);
		isLoadComplete = false;
		if (isGoneInit) {
			mImageView.setVisibility(View.VISIBLE);
		}
		mImageView.setImageDrawable(placeholder);
		if (mImgLoaderListener != null) {
			mImgLoaderListener.onLoadStarted();
		}
	}

	@Override
	public void onResourceReady(Z resource, @Nullable Transition<? super Z> transition) {
		super.onResourceReady(resource, transition);
		isLoadComplete = true;
		setImageDrawable(resource);
		if (isGoneInit) {
			mImageView.setVisibility(visibleInit);
		}
		if (mImgLoaderListener != null) {
			mImgLoaderListener.onLoadSuccess(resource);
		}
	}

	@Override
	public void onLoadFailed(@Nullable Drawable errorDrawable) {
		super.onLoadFailed(errorDrawable);
		isLoadComplete = true;
		if (isGoneInit) {
			mImageView.setVisibility(visibleInit);
		}
		if (mImgLoaderListener != null) {
			mImgLoaderListener.onLoadFailed(errorDrawable);
		}
	}

	@Override
	public void onLoadCleared(@Nullable Drawable placeholder) {
		super.onLoadCleared(placeholder);
		// 加载任务取消并且资源被释放时调用
		if (!isLoadComplete) {
			isLoadComplete = true;
			if (mImgLoaderListener != null) {
				mImgLoaderListener.onLoadCancelled(placeholder);
			}
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (isGoneInit) {
			mImageView.setVisibility(visibleInit);
		}
	}

	/**
	 * 设置图片
	 *
	 * @param
	 * @author zony
	 * @time 17-11-30 下午1:36
	 */
	private void setImageDrawable(Z resource) {
		if (resource != null) {
			if (resource instanceof Drawable) {
				mImageView.setImageDrawable((Drawable) resource);
			} else if (resource instanceof Bitmap) {
				mImageView.setImageBitmap((Bitmap) resource);
			}
		}
	}
}