package com.zony.imageload.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.zony.imageloadlib.listener.ImgLoaderListener;
import com.zony.imageloadlib.util.CustomExecutor;
import com.zony.imageloadlib.util.FileUtil;

import java.io.File;

public class CommonUtils {
    private static final String TAG = "CommonUtils";

    public static int dip2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 启动Activity
     *
     * @param context  上下文环境
     * @param intent   Intent实例
     * @param isFinish 是否关闭当前页面
     * @author zony
     * @time 18-6-19
     */
    public static void startActivity(Context context, Intent intent, boolean isFinish) {
        if (intent == null || context == null) {
            return;
        }
        try {
            context.startActivity(intent);
            if (isFinish) {
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存图片到相册
     *
     * @param context 上下文环境
     * @param url     图片地址
     * @author zony
     * @time 18-8-2
     */
    public static void savePicToGallery(final Context context, String url) {
        if (context == null) {
            LogUtil.w(TAG, "savePicToGallery context is null,retrun!");
            return;
        }
        ImgLoadUtil.downloadOnly(context, url, new ImgLoaderListener() {
            @Override
            public void onRequestFailed(Throwable throwable) {
                LogUtil.w(TAG, "onRequestFailed throwable: " + throwable.toString());
            }

            @Override
            public void onLoadSuccess(final Object resource) {
                CustomExecutor.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        File filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();
                        File appDir = new File(filePath, "zony");
                        if (!appDir.exists()) {
                            appDir.mkdirs();
                        }
                        File destFile = new File(appDir, System.currentTimeMillis() + ".jpg");
                        if (resource instanceof File) {
                            final boolean isSaveSuccess = FileUtil.copyFileByChannel(context, (File) resource, destFile, true);
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    if (isSaveSuccess) {
                                        Toast.makeText(context, "Save pic to Gallery success~", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "Save pic to Gallery fail!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }


    public static final String[] urls = new String[]{
            "http://img2.niutuku.com/desk/130220/37/37-niutuku.com-927.jpg",
            "http://img4.duitang.com/uploads/item/201508/20/20150820130718_dMrNS.thumb.700_0.jpeg",
            "http://img.shouji.baofeng.com/img/741/nl3741_short2821_448*252.667.gif",
            "http://img.shouji.baofeng.com/img/741/nl3741_short2111_448*252.610.jpg",
            "http://img.shouji.baofeng.com/img/741/nl3741_short5297_448*252.26.gif",
            "http://img.shouji.baofeng.com/img/550/v12898550_336*470.1.jpg",
            "http://img.shouji.baofeng.com/img/107/f107_a11547517_1056*389.30.jpg",
            "http://images.china.cn/attachement/jpg/site1000/20140910/001ec9591e621579f4bc06.jpg",
            "http://img2.niutuku.com/desk/1207/1035/ntk131818.jpg",
            "http://img.shouji.baofeng.com/img/741/nl3741_short2818_448*252.669.gif",
            "http://joymepic.joyme.com/article/uploads/20173/19149259273512896.jpeg",
            "http://image1.92bizhi.com/art_background-color-widescreen--04_02-1680x1050.jpg",
            "http://img.shouji.baofeng.com/img/741/nl3741_short5300_448*252.43.gif",
            "http://imgsrc.baidu.com/forum/pic/item/7bb4e2faaf51f3deb34f0aa294eef01f3829799d.jpg",
            "http://korea.people.com.cn/mediafile/201403/26/F201403261546202302528829.jpg",
            "http://img.shouji.baofeng.com/img/741/nl3741_short5292_448*252.51.gif",
            "http://img.shouji.baofeng.com/img/741/nl3741_short5535_448*252.519.jpg",
            "http://img.shouji.baofeng.com/img/741/nl3741_short3891_448*252.505.jpg",
            "http://img1.gtimg.com/ent/pics/hv1/213/155/1650/107330988.jpg",
            "http://img.shouji.baofeng.com/img/741/nl3741_short2683_448*252.218.gif",
            "http://img002.21cnimg.com/photos/album/20141128/o/172604880FB6F503BBE7961A0DF74F87.png",
            "http://img1.gtimg.com/ent/pics/hv1/218/49/1421/92413238.jpg",
            "http://img.shouji.baofeng.com/img/741/nl3741_short5292_448*252.52.gif",
            "http://photo.sohu.com/2004/03/12/07/Img219410724.jpg",
            "http://news.k618.cn/pic/top/201312/W020131224747210015941.jpg",
            "http://img.shouji.baofeng.com/img/517/v11547517_336*470.3.jpg",
            "http://img.shouji.baofeng.com/img/741/nl3741_short2815_448*252.646.gif",
            "http://img.shouji.baofeng.com/img/107/f107_a13083719_1056*389.9.jpg",
            "http://img.shouji.baofeng.com/img/719/v13083719_336*470.1.jpg",
            "http://img.shouji.baofeng.com/img/new/95/f8/95f83e8d1473a39153f79bcfd7b216f7_star_s.jpg",
            "http://img.shouji.baofeng.com/img/new/33/f9/33f93d785160658fe3dfccc1f6221eee_star_s.jpg",
            "http://img.shouji.baofeng.com/img/107/f107_a11265796_1056*389.84.jpg",
            "http://img.shouji.baofeng.com/img/796/v11265796_336*470.3.jpg",
            "http://img.shouji.baofeng.com/img/179/v12765179_336*470.3.jpg",
            "http://img.shouji.baofeng.com/img/107/f107_a12765179_1056*389.11.jpg"
    };

}
