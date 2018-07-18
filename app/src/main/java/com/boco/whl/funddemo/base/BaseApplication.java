package com.boco.whl.funddemo.base;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import com.blankj.utilcode.util.Utils;
import com.boco.whl.funddemo.utils.ToastUtil;
import com.boco.whl.funddemo.utils.net.NetBroadcastReceiver;
import com.boco.whl.funddemo.utils.net.NetUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * 基类baseApplication  代表应用程序
 *
 * @author Administrator
 */
public class BaseApplication extends Application implements NetBroadcastReceiver.NetEvent {
    private static Context context;
    public static NetBroadcastReceiver.NetEvent event;
    public static String VALUE = "honglei92";
    private static int netResponseTime;
    private RefWatcher refWatcher;
    private static Handler handler;
    /**
     * 网络类型
     */
    private int netMobile;

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
//        SDKInitializer.initialize(getApplicationContext());
        super.onCreate();
        //初始化context
        context = this;
        //LeakCanary内存泄露检测
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);
        //日志工具
        Logger.addLogAdapter(new AndroidLogAdapter());
        Utils.init(context);
        //bugly异常上报
        CrashReport.initCrashReport(context, "29ca5eaac5", false);

        event = this;
        //网络监听
        inspectNet();

        VALUE = "wanghonglei";
        //初始化handler
        handler = new Handler();

        registerComponentCallbacks(new ComponentCallbacks2() {
            @Override
            public void onTrimMemory(int level) {

            }

            @Override
            public void onConfigurationChanged(Configuration newConfig) {

            }

            @Override
            public void onLowMemory() {

            }
        });
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Logger.d("onActivityCreated: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Logger.d("onActivityStarted: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Logger.d("onActivityResumed: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Logger.d("onActivityPaused: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Logger.d("onActivityStopped: " + activity.getLocalClassName());
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Logger.d("onActivitySaveInstanceState: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Logger.d("onActivityDestroyed: " + activity.getLocalClassName());
            }
        });
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

    @Override
    public void onTerminate() {
        super.onTerminate();
        Logger.d("结束了！！！");
    }

    public static Handler getHandler() {
        return handler;
    }

    public static Context getContext() {
        return context;
    }

    public static void setNetResponseTime(int time) {
        netResponseTime = time;
    }
}
