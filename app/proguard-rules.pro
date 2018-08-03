# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/zony/software/icy/android-sdk-linux/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.

# To enable ProGuard in your project, edit project.properties
# to define the proguard.config property as described in that file.
#
# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in ${sdk.dir}/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the ProGuard
# include property in project.properties.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepattributes *Annotation*
-keepattributes *JavascriptInterface*

# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclassmembers
-dontskipnonpubliclibraryclasses
-verbose

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
#-dontoptimize
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers public class * {
   public void onEvent*(***);
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**
-dontwarn android.app.**

-ignorewarnings

# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

-keep class android.support.v7.widget.RecyclerView{*;}
-keep class android.support.v7.widget.RecyclerView$Adapter{*;}
-keep class android.support.v7.widget.RecyclerView$*{*;}
-keep class android.support.v7.widget.RecyclerView$LayoutManager{*;}
-keep class android.support.v7.widget.GridLayoutManager{*;}
-keep class android.support.v7.widget.LinearLayoutManager{*;}
-keep class android.support.v4.app.*{*;}
-keep class android.support.v4.app.Fragment{*;}
-keep class android.support.v4.app.FragmentActivity{*;}
-keep class android.support.v4.app.NotificationCompat{*;}
-keep class android.support.v4.app.FragmentTabHost{*;}
-keep class android.support.v4.app.FragmentController{*;}
-keep class android.support.v4.app.FragmentStatePagerAdapter{*;}
-keep class android.support.v4.app.FragmentManager{*;}
-keep class android.support.v4.app.FragmentTransaction{*;}
-keep class android.support.v4.view.animation.FastOutSlowInInterpolator{*;}

-keep class android.support.v4.view.ViewPager{*;}
-keep class android.support.v4.view.ViewPager$OnPageChangeListener{*;}
-keep interface android.support.v4.view.ViewPager$OnPageChangeListener{*;}
-keep class android.support.v4.view.PagerAdapter{*;}
-keep class android.support.v4.view.ViewCompat{*;}
-keep class android.support.v4.view.MotionEventCompat{*;}

-keep class android.support.v4.app.Fragment$SavedState
-keep class android.support.v4.widget.ViewDragHelper{*;}
-keep class android.support.v4.widget.ViewDragHelper$*{*;}
-keep class android.support.v4.util.ArrayMap{*;}
-keep class android.support.v4.content.ContextCompat{*;}
-keep class android.support.v4.view.animation.PathInterpolatorCompat{*;}
-keep class android.support.v4.util.SparseArrayCompat{*;}

#glide imageload start
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#glide imageload end





