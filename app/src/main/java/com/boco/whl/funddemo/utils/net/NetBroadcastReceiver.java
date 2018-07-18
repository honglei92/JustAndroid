package com.boco.whl.funddemo.utils.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.boco.whl.funddemo.base.BaseApplication;

/**
 * author: wanghonglei@boco.com.cn
 * desc:
 * createTime: 2017/8/26 0026
 * updateTime: 2017/8/26 0026
 */

public class NetBroadcastReceiver extends BroadcastReceiver {

    public NetEvent event = BaseApplication.event;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtil.getNetWorkState(context);
            // 接口回调传过去状态的类型
            event.onNetChange(netWorkState);
        }
    }

    // 自定义接口
    public interface NetEvent {
        void onNetChange(int netMobile);
    }
}