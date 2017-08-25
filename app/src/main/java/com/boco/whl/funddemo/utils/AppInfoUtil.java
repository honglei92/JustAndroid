package com.boco.whl.funddemo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 获取应用基本信息的工具类
 * Created by zhang.w.x on 2017/4/13.
 */
public class AppInfoUtil {
    /**
     * 获取应用版本号
     * @param mContext
     * @return
     */
    public static int getAppVersionCode(Context mContext) {
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
