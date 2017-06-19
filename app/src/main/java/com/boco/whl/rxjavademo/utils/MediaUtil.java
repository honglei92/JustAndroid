package com.boco.whl.rxjavademo.utils;

import android.text.TextUtils;

/**
 * Created by zhang.w.x on 2017/3/28.
 */
public class MediaUtil {

    public static String getFileNameByUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }

        int index = url.lastIndexOf("/");
        if (index == -1) {
            return "";
        }

        return url.substring(index, url.length());
    }
}
