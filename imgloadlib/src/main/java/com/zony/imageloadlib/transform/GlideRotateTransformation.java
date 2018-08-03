package com.zony.imageloadlib.transform;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * 倾斜角度
 *
 * @param
 * @author storm
 * @time 17-4-27 上午10:13
 */
public class GlideRotateTransformation extends BitmapTransformation {

	private float rotateRotationAngle = 0f;

	public GlideRotateTransformation(float rotateRotationAngle) {
		super();
		this.rotateRotationAngle = rotateRotationAngle;
	}

	@Override
	protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
		Matrix matrix = new Matrix();

		matrix.postRotate(rotateRotationAngle);

		return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
	}

	@Override
	public void updateDiskCacheKey(MessageDigest messageDigest) {

	}
}