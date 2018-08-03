package com.zony.imageloadlib.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 获取线程池的唯一对象
 *
 * @author zony
 * @time 18-6-13
 */
@SuppressWarnings("ALL")
public class CustomExecutor {

    private static volatile CustomExecutor instance = null;

    private static volatile ExecutorService executor = null;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "BfDownloadThread #" + mCount.getAndIncrement());
        }
    };

    /**
     * 私有构造函数
     *
     * @author zony
     * @time 18-6-20
     */
    private CustomExecutor() {
    }

    /**
     * CustomExecutor
     *
     * @return
     */
    public static CustomExecutor getInstance() {
        if (instance == null) {
            synchronized (CustomExecutor.class) {
                if (instance == null) {
                    instance = new CustomExecutor();
                    executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                            60L, TimeUnit.SECONDS,
                            new SynchronousQueue<Runnable>(),
                            sThreadFactory);
                }
            }
        }
        return instance;
    }

    /**
     * 获取内部封装的Executor
     *
     * @return
     */
    public java.util.concurrent.Executor getInternalExecutor() {
        return executor;
    }

    /**
     * 封装了CachedThreadPool的execute方法
     */
    public void execute(Runnable command) {
        executor.execute(command);
    }


    /**
     * 封装了CachedThreadPool的submit方法
     */
    public <T> Future<T> submit(Callable<T> task) {
        return executor.submit(task);
    }
}
