package com.boco.whl.rxjavademo.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Android6.0权限处理工具类
 * <p/>
 * Created by LiuJie on 2016/7/20.
 */
public class PermissionsUT {
    private static PermissionsUT permissionsUT = null;

    private final List<String> Permissions = new ArrayList<String>() {{// 非默认启用的权限集合
        add(Manifest.permission.ACCESS_COARSE_LOCATION);
        add(Manifest.permission.ACCESS_FINE_LOCATION);
        add(Manifest.permission.ACCESS_WIFI_STATE);
        add(Manifest.permission.ACCESS_NETWORK_STATE);
        add(Manifest.permission.READ_PHONE_STATE);
        add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        add(Manifest.permission.INTERNET);
    }};

    private PermissionsUT() {
    }

    public synchronized static PermissionsUT getInstance() {
        if (permissionsUT == null) {
            permissionsUT = new PermissionsUT();
        }
        return permissionsUT;
    }

    /**
     * 扫描权限集合 如果发现未启用权限，停止扫描并启动权限动态请求。
     *
     * @param isRequest 是否发起系统权限请求
     * @return 如果有未启用的权限返回true
     */
    public boolean checkPermissions(Activity activity, boolean isRequest) {
        for (int index = 0; index < Permissions.size(); index++) {
            if (ContextCompat.checkSelfPermission(activity, Permissions.get(index)) == PackageManager.PERMISSION_DENIED) {
                if (isRequest)
                    ActivityCompat.requestPermissions(activity, new String[]{Permissions.get(index)}, index);
                return true;
            }
        }
        return false;
    }

    public boolean checkPermissions(Activity activity, boolean isRequest, List<String> permissions) {
        for (int index = 0; index < permissions.size(); index++) {
            if (ContextCompat.checkSelfPermission(activity, permissions.get(index)) == PackageManager.PERMISSION_DENIED) {
                if (isRequest)
                    ActivityCompat.requestPermissions(activity, new String[]{permissions.get(index)}, index);
                return true;
            }
        }
        return false;
    }

    /**
     * 扫描权限集合 如果发现未启用权限，停止扫描并启动权限动态请求。
     *
     * @param activity    发起请求的Activity
     * @param intentClass 扫描通过后被跳转的Class
     * @param isRequest   是否发起系统权限请求
     * @return 如果有未启用的权限返回true
     */
    public void checkPermissions(Activity activity, Class intentClass, boolean isRequest) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!checkPermissions(activity, isRequest)) {
                IntentUT.getInstance().openActivity(activity, intentClass, true);
            }
        } else {
            IntentUT.getInstance().openActivity(activity, intentClass, true);
        }
    }

    public void checkPermissions(Activity activity, Class intentClass, boolean isRequest, List<String> permissions) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!checkPermissions(activity, isRequest, permissions)) {
                IntentUT.getInstance().openActivity(activity, intentClass, true);
                activity.finish();
            }
        } else {
            IntentUT.getInstance().openActivity(activity, intentClass, true);
        }
    }
}
