import imageload

1、根目录build.gradle repositories下添加如下：

        maven { url 'https://maven.google.com' }

2、引入imgloadlib Module

2、copy com.zony.imageload.utils.ImgLoadUtil

3、Application onCreate中添加如下代码：

    ImgLoadUtil.setTagId();

4、AndroidManifest.xml中加入： 

    <meta-data
                android:name="com.zony.imageloadlib.CustomGlideModule"
                android:value="GlideModule" />

5、添加混淆

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

