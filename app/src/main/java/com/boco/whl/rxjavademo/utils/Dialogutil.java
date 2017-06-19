package com.boco.whl.rxjavademo.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhang.w.x on 2017/3/14.
 */
public class Dialogutil {
    /**
     * 显示toast
     *
     * @param context
     * @param text
     */
    public static void showShortToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示toast
     *
     * @param context
     * @param text
     */
    public static void showLongtToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void showNetWorkErrorToast(Context context, int stat) {
        if (stat == 504) {
            showShortToast(context, "服务器出错！");
        } else if (stat == 404) {
            showShortToast(context, "地址未找到！");
        } else {
            showShortToast(context, "访问服务出错！");
        }
    }

    public static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog waitingDialog = new ProgressDialog(context);
        waitingDialog.setCancelable(false);
        waitingDialog.setCanceledOnTouchOutside(false);

        return waitingDialog;
    }
}
