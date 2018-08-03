package com.zony.imageloadlib.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.zony.imageload.utils.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 文件工具类
 *
 * @author zony
 * @time 18-8-2
 */
public class FileUtil {
    private static final String TAG = "FileUtil";

    /**
     * 使用文件通道的方式复制文件
     * 比文件流复制的速度更快，大文件比较明显，30%左右
     *
     * @param srcFile  源文件
     * @param destFile 复制到的新文件
     */
    public static boolean copyFileByChannel(final Context context, File srcFile, File destFile, boolean isUpdatePhoto) {
        boolean isSaveSuccess = false;
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(srcFile);
            fo = new FileOutputStream(destFile);
            LogUtil.i(TAG, "destFile: " + destFile.getAbsolutePath());
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道

            if (context != null && isUpdatePhoto) {
                //通知图库更新
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(destFile.getPath()))));
                isSaveSuccess = true;
            }
        } catch (IOException e) {
            isSaveSuccess = false;
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                isSaveSuccess = false;
                e.printStackTrace();
            }
        }
        return isSaveSuccess;
    }

}
