package com.boco.whl.funddemo.module.activity.regulation.threadcommunication.thread;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * <pre>
 *  author : honglei92
 *  desc :
 *  blog :
 *  createtime : 2017/6/19 0019 17:52
 *  updatetime : 2017/6/19 0019 17:52
 * </pre>
 */
public class MyProducer extends Thread {
    private PipedOutputStream outputStream;
    private int index = 0;

    public MyProducer(PipedOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        while (index < 5) {
            try {
                for (int i = 0; i < 5; i++) {
                    outputStream.write(index++);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
