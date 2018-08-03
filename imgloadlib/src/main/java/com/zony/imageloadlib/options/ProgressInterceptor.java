package com.zony.imageloadlib.options;

import com.zony.imageloadlib.listener.ProgressListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 进度拦截器
 *
 * @author zony
 * @time 18-8-1
 */
public class ProgressInterceptor implements Interceptor {

    /**
     * 进度监听MAP
     */
    protected static final Map<String, ProgressListener> LISTENER_MAP = new HashMap<>();

    /**
     * 添加监听到MAP中
     *
     * @param url      图片地址
     * @param listener 进度监听
     * @author zony
     * @time 18-8-1
     */
    public static void addListener(String url, ProgressListener listener) {
        LISTENER_MAP.put(url, listener);
    }

    /**
     * 移除进度监听
     *
     * @param url 图片地址
     * @author zony
     * @time 18-8-1
     */
    public static void removeListener(String url) {
        LISTENER_MAP.remove(url);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String url = request.url().toString();
        ResponseBody body = response.body();
        Response newResponse = response.newBuilder().body(new ProgressResponseBody(url, body)).build();
        return newResponse;
    }
}