package com.boco.whl.rxjavademo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/11.
 */
public class ToastUtil {

    private static Toast toast;
    public static void showToast(Context context, String str){
        if (toast == null){
            toast = Toast.makeText(context,"",Toast.LENGTH_SHORT);
        }
        toast.setText(str);
        toast.show();
    }
}
