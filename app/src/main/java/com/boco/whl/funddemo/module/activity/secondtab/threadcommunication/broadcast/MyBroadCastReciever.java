package com.boco.whl.funddemo.module.activity.secondtab.threadcommunication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;

/**
 * <pre>
 *  author : honglei92
 *  desc :广播接收器
 *  blog :
 *  createtime : 2017/6/20 0020 15:30
 *  updatetime : 2017/6/20 0020 15:30
 * </pre>
 */
public class MyBroadCastReciever extends BroadcastReceiver {
    public static String tag = "MyBroadCastReciever";
    private static int m = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        LogUtils.d(tag, bundle.getString("name") + " = " + m);
        m++;
    }
}
