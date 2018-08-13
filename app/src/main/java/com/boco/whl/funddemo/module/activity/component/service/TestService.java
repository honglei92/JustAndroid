package com.boco.whl.funddemo.module.activity.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.boco.whl.funddemo.base.BaseTinkerApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author:honglei92
 * @time:2018/7/18
 */
public class TestService extends Service {
    static String TAG = "TestService";
    private OnProgressListener mProgressListener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "TestService onBind");
        Log.d("honglei-process", android.os.Process.myPid() + "");

        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "TestService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "TestService onStartCommand");

        List<Integer> list;
        for (int i = 0; i < 10; i++) {
            list = new ArrayList<>();
            for (int i1 = 0; i1 < 100; i1++) {
                list.add((int) (100 * Math.random()));
            }
            System.out.println(BaseTinkerApplication.VALUE + Arrays.toString(list.toArray()));

        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "TestService destroyed");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "TestService onUnbind");
        return super.onUnbind(intent);
    }

    public void showInfo() {
        Log.d(TAG, "showInfo: 我来自TestService");
    }

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.mProgressListener = onProgressListener;
    }

    public void startDownLoad() {
        try {
            Thread.sleep(2100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long totalMills = 10 * 1000;
        CountDownTimer timer = new CountDownTimer(totalMills, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                mProgressListener.setProgress((int) (totalMills - millisUntilFinished) / 100);
                Log.d("whl", millisUntilFinished + "");

            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }

    /**
     * 自定义Binder继承自Binder
     */
    class MyBinder extends Binder {
        public TestService getService() {
            return TestService.this;
        }

    }

    public MyBinder myBinder = new MyBinder();

    public interface OnProgressListener {
        void setProgress(int progress);
    }
}
