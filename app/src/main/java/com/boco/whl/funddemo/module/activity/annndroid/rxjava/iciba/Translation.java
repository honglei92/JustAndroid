package com.boco.whl.funddemo.module.activity.annndroid.rxjava.iciba;

import android.util.Log;

/**
 * @author honglei92
 * @createTime 2018/5/8 0008
 */
public class Translation {
    private int status;
    private Content content;

    private static class Content {
        private String from;
        private String to;
        private String vender;
        private String out;
        private int errNo;
    }

    public void show() {
        Log.d("RxJava", content.out);
    }
}
