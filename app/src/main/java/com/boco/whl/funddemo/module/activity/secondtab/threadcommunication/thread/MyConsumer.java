package com.boco.whl.funddemo.module.activity.secondtab.threadcommunication.thread;

import com.boco.whl.funddemo.utils.LogUtil;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/6/19 0019 21:34
 *  updatetime : 2017/6/19 0019 21:34
 * </pre>
 */
public class MyConsumer extends Thread {
    private PipedInputStream inputStream;

    public MyConsumer(PipedInputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                int count = inputStream.available();
                if (count > 0) {
                    LogUtil.d("rest product count: " + count);
                    LogUtil.d("get product: " + inputStream.read());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
