开源图片加载框架二次封装（Glide为例）

此DEMO为Glide图片框架二次封装，为了实现项目中更简单的更新替换图片加载框架
## 功能：
    1、使用okhttp请求图片
    2、图片加载进度显示
    3、查看大图以及保存图片到相册

## 使用说明：

#### 1、引入imageloadlib module，在主工程build.gradle中加入

    dependencies {
        compile project(':imageloadlib')
    }
	如果需要对图片进行修剪处理请加上如下两个库
	//裁剪库
	compile 'jp.wasabeef:glide-transformations:3.0.1'
	// If you want to use the GPU Filters
	compile 'jp.co.cyberagent.android.gpuimage:gpuimage-library:1.4.1'

#### 2、主工程中添加如下权限

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

#### 3、主工程中添加混淆

    #glide imageload start
    -keep public class * implements com.bumptech.glide.module.GlideModule
    -keep public class * extends com.bumptech.glide.AppGlideModule
    -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
      **[] $VALUES;
      public *;
    }

    -dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder

    # for DexGuard only
    -keepresourcexmlelements manifest/application/meta-data@value=GlideModule
    #glide imageload end

#### 4、项目中使用

    ImageLoaderOptions options = new ImageLoaderOptions.Builder()
        .errorId(R.mipmap.ic_launcher)
        .placeholderId(R.mipmap.ic_launcher)
        .diskCacheStrategy(ImageLoaderOptions.DiskCache.SOURCE)
        .animator(android.R.anim.slide_in_left)
        .isFitCenter(true)
        .transform(new GlideRotateTransformation(getActivity(), 60))
        .skipMemoryCache(false)
        .build();
    ImageLoader.getInstance().showImage(context, path, imageView, options);

## import imageloadlib：
#### 1、根目录build.gradle repositories下添加如下：

        maven { url 'https://maven.google.com' }

#### 2、引入imgloadlib Module

#### 2、copy com.zony.imageload.utils.ImgLoadUtil

#### 3、Application onCreate中添加如下代码：

    ImgLoadUtil.setTagId();

#### 4、AndroidManifest.xml中加入：

    <meta-data
                android:name="com.zony.imageloadlib.CustomGlideModule"
                android:value="GlideModule" />


## 遇到相关问题及解决方案

#### 问题一、You must not call setTag() on a view Glide is targeting
遇到该错误 报错原因大致是因为Glide加载的iamgeView调用了setTag()方法导致的错误，因为Glide已经默认为ImageView设置的Tag
相关解决方案已经在Glide 3.6.0（issue #370）被引进，实测可行

#### 1、在AndroidManifest.xml中加入

    <application
        android:name=".App">
#### 2、在App中添加如下代码：

    public class App extends Application {
        @Override
        public void onCreate() {
        super.onCreate();
            ViewTarget.setTagId(R.id.glide_tag);
        }
    }

#### 3、在src/main/values/ids.xml添加如下代码：

    <resources>
        <item type="id" name="glide_tag" />
    </resources>


