package com.boco.whl.funddemo.base;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.Utils;

/**
 * Created by honglei92 on 2017/4/21 0021.
 */

public class BaseApplication extends Application {
    private Context context = this;

    @Override
    public void onCreate() {
        SDKInitializer.initialize(getApplicationContext());
        super.onCreate();
        Utils.init(context);
    }
}
