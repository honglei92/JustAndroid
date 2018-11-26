package com.boco.whl.funddemo.module.activity.regulation.threadcommunication;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

class HandlerUtil {
    private Handler handler;

    HandlerUtil() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
                Looper.prepare();
                handler = new Handler(Looper.myLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if (msg.what == 123) {
                            Log.i("honglei92", msg.obj.toString());
                        }
                    }
                };
                Looper.loop();
            }
        };
        t1.start();
    }


    void addTask(int i) {
        if (handler != null) {
            Message message = new Message();
            message.what = 123;
            message.obj = "欢迎回家" + i;
            handler.sendMessage(message);
        }
    }
}
