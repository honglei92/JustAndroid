package com.boco.whl.funddemo.base;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.Utils;
import com.boco.whl.funddemo.utils.ToastUtil;
import com.boco.whl.funddemo.utils.net.NetBroadcastReceiver;
import com.boco.whl.funddemo.utils.net.NetUtil;

/**
 * 基类baseApplication
 */
public class BaseApplication extends Application implements NetBroadcastReceiver.NetEvent {
    private Context context = this;
    public static NetBroadcastReceiver.NetEvent event;
    /**
     * 网络类型
     */
    private int netMobile;


    @Override
    public void onCreate() {
        SDKInitializer.initialize(getApplicationContext());
        super.onCreate();
        Utils.init(context);
        event = this;
        inspectNet();
    }

    /**
     * 初始化时判断有没有网络
     */
    private boolean inspectNet() {
        this.netMobile = NetUtil.getNetWorkState(context);
        return isNetConnect();
    }

    private boolean isNetConnect() {
        if (netMobile == 1 || netMobile == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onNetChange(int netMobile) {
        String netCondition = "";
        if (netMobile == 1) {
            netCondition = "当前WIFI已连接";
        } else if (netMobile == 0) {
            netCondition = "当前移动数据流量已连接";
        } else {
            netCondition = "网络已断开";
        }
        ToastUtil.showToast(context, netCondition);
    }
}
