package com.boco.whl.funddemo.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Intent跳转工具类
 * <p/>
 * 包含了是否带参数、是否带返回跳转方法
 */
public class IntentUT {
    private static IntentUT intentUT = null;

    private IntentUT() {
    }

    public synchronized static IntentUT getInstance() {
        if (intentUT == null) {
            intentUT = new IntentUT();
        }
        return intentUT;
    }

    /**
     * ===================================================================
     * 普通Intent跳转方法
     *
     * @param activity 跳转的原Activity
     * @param clss     跳转的目标Class
     * @param isFinish 是否关闭元Activity
     */
    public void openActivity(Activity activity, Class<?> clss, boolean isFinish) {
        Intent intent = new Intent(activity, clss);
        activity.startActivity(intent);
        if (isFinish)
            activity.finish();
    }

    public void openActivity(Activity activity, Class<?> clss, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(activity, clss);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        if (isFinish)
            activity.finish();
    }

    /**
     * ===================================================================
     * 带参数的Intent跳转方法
     *
     * @param activity    跳转的原Activity
     * @param clss        跳转的目标Class
     * @param requestCode 跳转时所带的参数
     */
    public void openActivityForResult(Activity activity, Class<?> clss, int requestCode, boolean isFinish) {
        Intent intent = new Intent(activity, clss);
        activity.startActivityForResult(intent, requestCode);
        if (isFinish)
            activity.finish();
    }

    public void openActivityForResult(Activity activity, Class<?> clss, int requestCode, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(activity, clss);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
        if (isFinish)
            activity.finish();
    }
}
