package com.boco.whl.funddemo.module.activity.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.boco.whl.funddemo.base.BaseApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author:honglei92
 * @time:2018/7/18
 */
public class TestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        List<Integer> list;
        for (int i = 0; i < 10; i++) {
            list = new ArrayList<>();
            for (int i1 = 0; i1 < 100; i1++) {
                list.add((int) (100 * Math.random()));
            }
            System.out.println(BaseApplication.VALUE + Arrays.toString(list.toArray()));
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
