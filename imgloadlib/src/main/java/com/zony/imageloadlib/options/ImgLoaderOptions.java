package com.zony.imageloadlib.options;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.transition.ViewPropertyTransition;


/**
 * Builder模式的图片各种设置
 *
 * @param
 * @author zony
 * @time 17-4-27 下午3:21
 */
public class ImgLoaderOptions {
    private final int placeholderId;//默认占位资源
    private final int errorId;//错误时显示的资源
    private final int crossDuration; //淡入淡出动画持续的时间
    private final int overrideHeight; //重新设置Target的宽高值（单位为pixel）
    private final int overrideWidth;
    private final DiskCache diskCacheStrategy; //硬盘缓存,默认为all类型
    private final LoadPriority priority;//加载优先级策略
    private final boolean skipMemoryCache;//是否跳过内存缓存
    private final Drawable placeholderDrawable;
    private final Drawable errorPlaceholder;
    private final Transformation<Bitmap> transform; //位图转换
    private final ViewPropertyTransition.Animator animator; //自定义动画
    private final boolean isCenterCrop;//缩放图像让它填充到ImageView界限内并且裁剪额外的部分。ImageView可能会完全填充，但图像可能不会完整显示。
    private final boolean isFitCenter;//缩放图像让图像都测量出来等于或小于ImageView的边界范围。该图像将会完全显示，但可能不会填满整个ImageView
    private final float thumbnail;//先加载缩略图比例
    private final Transformation<Bitmap>[] transformations;

    private ImgLoaderOptions(Builder builder) {
        this.placeholderId = builder.placeholderId;
        this.errorId = builder.errorId;
        this.crossDuration = builder.crossDuration;
        this.overrideHeight = builder.overrideHeight;
        this.overrideWidth = builder.overrideWidth;
        this.diskCacheStrategy = builder.diskCacheStrategy;
        this.priority = builder.priority;
        this.skipMemoryCache = builder.skipMemoryCache;
        this.placeholderDrawable = builder.placeholderDrawable;
        this.errorPlaceholder = builder.errorPlaceholder;
        this.transform = builder.transform;
        this.animator = builder.animator;
        this.isCenterCrop = builder.isCenterCrop;
        this.isFitCenter = builder.isFitCenter;
        this.thumbnail = builder.thumbnail;
        this.transformations = builder.transformations;
    }

    public Transformation<Bitmap> transform() {
        return transform;
    }

    public Transformation<Bitmap>[] transforms() {
        return transformations;
    }

    public float thumbnail() {
        return thumbnail;
    }

    public boolean isCenterCrop() {
        return isCenterCrop;
    }

    public boolean isFitCenter() {
        return isFitCenter;
    }

    public Drawable placeholderDrawable() {
        return placeholderDrawable;
    }

    public Drawable errorPlaceholder() {
        return errorPlaceholder;
    }

    public boolean isSkipMemoryCache() {
        return skipMemoryCache;
    }

    public LoadPriority getPriority() {
        return priority;
    }

    public int getPlaceholderId() {
        return placeholderId;
    }

    public int getErrorId() {
        return errorId;
    }

    public int getCrossDuration() {
        return crossDuration;
    }

    public int getOverrideHeight() {
        return overrideHeight;
    }

    public int getOverrideWidth() {
        return overrideWidth;
    }

    public DiskCache getDiskCacheStrategy() {
        return diskCacheStrategy;
    }

    public static class Builder {
        private int placeholderId;
        private int errorId;
        private int crossDuration;
        private int overrideHeight;
        private int overrideWidth;
        private DiskCache diskCacheStrategy;
        private LoadPriority priority;
        private boolean skipMemoryCache;
        private Drawable placeholderDrawable;
        private Drawable errorPlaceholder;
        private Transformation<Bitmap> transform;
        private ViewPropertyTransition.Animator animator;
        private boolean isCenterCrop;
        private boolean isFitCenter;
        private float thumbnail;
        private Transformation<Bitmap>[] transformations;

        public Builder() {
            placeholderId = -1;
            errorId = -1;
            crossDuration = 0;
            overrideHeight = 0;
            overrideWidth = 0;
            diskCacheStrategy = DiskCache.RESULT;
            priority = LoadPriority.NORMAL;
            skipMemoryCache = false;
            isCenterCrop = false;
            isFitCenter = false;
            thumbnail = 0;
        }

        public Builder transforms(Transformation<Bitmap>... transformations) {
            this.transformations = transformations;
            return this;
        }

        public Builder thumbnail(float thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder isCenterCrop(boolean isCenterCrop) {
            this.isCenterCrop = isCenterCrop;
            return this;
        }

        public Builder isFitCenter(boolean isFitCenter) {
            this.isFitCenter = isFitCenter;
            return this;
        }

        public Builder animator(ViewPropertyTransition.Animator animator) {
            this.animator = animator;
            return this;
        }

        public Builder transform(Transformation<Bitmap> transform) {
            this.transform = transform;
            return this;
        }

        public Builder placeholderDrawable(Drawable placeholderDrawable) {
            this.placeholderDrawable = placeholderDrawable;
            return this;
        }

        public Builder errorPlaceholder(Drawable errorPlaceholder) {
            this.errorPlaceholder = errorPlaceholder;
            return this;
        }

        public Builder skipMemoryCache(boolean skipMemoryCache) {
            this.skipMemoryCache = skipMemoryCache;
            return this;
        }

        public Builder priority(LoadPriority priority) {
            this.priority = priority;
            return this;
        }

        public Builder diskCacheStrategy(DiskCache diskCacheStrategy) {
            this.diskCacheStrategy = diskCacheStrategy;
            return this;
        }

        public Builder override(int overrideWidth, int overrideHeight) {
            this.overrideHeight = overrideHeight;
            this.overrideWidth = overrideWidth;
            return this;
        }

        public Builder crossDuration(int crossDuration) {
            this.crossDuration = crossDuration;
            return this;
        }

        public Builder placeholderId(int placeholderId) {
            this.placeholderId = placeholderId;
            return this;
        }

        public Builder errorId(int errorId) {
            this.errorId = errorId;
            return this;
        }

        public ImgLoaderOptions build() {
            return new ImgLoaderOptions(this);
        }
    }

    /**
     * 硬盘缓存策略
     */
    public enum DiskCache {
        NONE(DiskCacheStrategy.NONE),//跳过硬盘缓存
        SOURCE(DiskCacheStrategy.DATA),//仅仅保存原始分辨率的图片
        RESULT(DiskCacheStrategy.RESOURCE),//仅仅缓存最终分辨率的图像(降低分辨率后的或者转换后的)
        ALL(DiskCacheStrategy.ALL),//使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据
        AUTOMATIC(DiskCacheStrategy.AUTOMATIC);//根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。默认行为
        private DiskCacheStrategy strategy;

        DiskCache(DiskCacheStrategy strategy) {
            this.strategy = strategy;
        }

        public DiskCacheStrategy getStrategy() {
            return strategy;
        }
    }

    /**
     * 加载优先级策略
     */
    public enum LoadPriority {
        LOW(Priority.LOW),
        NORMAL(Priority.NORMAL),
        HIGH(Priority.HIGH),
        IMMEDIATE(Priority.IMMEDIATE),;
        Priority priority;

        LoadPriority(Priority priority) {
            this.priority = priority;
        }

        public Priority getPriority() {
            return priority;
        }
    }
}