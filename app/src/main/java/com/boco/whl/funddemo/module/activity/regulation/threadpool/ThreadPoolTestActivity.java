package com.boco.whl.funddemo.module.activity.regulation.threadpool;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.boco.whl.funddemo.R;
import com.boco.whl.funddemo.base.BaseActivity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author honglei92
 */
public class ThreadPoolTestActivity extends BaseActivity {
    private static final String TAG = "wThreadPoolTestActivity";
    /**
     * 线程工厂
     */
    static ThreadFactory threadFactory = new ThreadFactory() {
        public final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "TestTask #" + mCount.getAndIncrement());
        }
    };
    /**
     * 线程池
     */
    static ThreadPoolExecutor THREAD_POOL_EXECUTOR;

    /**
     * 带锁的阻塞队列
     */
    static BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<Runnable>(20);

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4, 10, 30, TimeUnit.SECONDS, blockingQueue, threadFactory);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        doTest();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_di_di;
    }

    private void doTest() {
        //创建30个任务到队列
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            THREAD_POOL_EXECUTOR.execute(() -> {
                Log.d(TAG, "task:" + finalI);
                Log.d(TAG, "poolSize:" + THREAD_POOL_EXECUTOR.getPoolSize());
                Log.d(TAG, "corePoolSize:" + THREAD_POOL_EXECUTOR.getCorePoolSize());
                Log.d(TAG, "maximumPoolSize:" + THREAD_POOL_EXECUTOR.getMaximumPoolSize());
                Log.d(TAG, "largestPoolSize:" + THREAD_POOL_EXECUTOR.getLargestPoolSize());
                Log.d(TAG, "queue:" + THREAD_POOL_EXECUTOR.getQueue().size());
            });
        }
    }
}
