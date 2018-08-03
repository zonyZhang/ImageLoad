package com.zony.imageload;

import android.app.Application;
import android.content.res.Configuration;

import com.zony.imageload.utils.ImgLoadUtil;

public class ImageLoadApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImgLoadUtil.setTagId();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
