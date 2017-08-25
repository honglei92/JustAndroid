package com.boco.whl.funddemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static com.boco.whl.funddemo.utils.Constant.KEY.KEY_USER_CONFIG;


/**
 * Created by zhang.w.x on 2017/3/16.
 */
public class SharedPreferencesUtil {
    public static final String KEY_TOKEN_ID = "tokenId";

    public static final String KEY_REGIONS = "regions";
    /*上一个清理缓存的版本*/
    public static final String KEY_PRECLEAN_VERSION = "preCleanVersion";


    /**
     * 设置登录用户号码
     *
     * @param context
     * @param userId
     */
    public static void setLoginUserPhone(Context context, String userId) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY_USER_CONFIG, Context.MODE_PRIVATE).edit();
        editor.putString(Constant.KEY.LOGIN_PHONE_NUMBER, userId);
        editor.commit();
    }

    /**
     * 获取登录用户号码
     *
     * @param context
     * @return
     */
    public static String getPreLoginUserPhone(Context context) {
        return context.getSharedPreferences(KEY_USER_CONFIG, Context.MODE_PRIVATE).getString(Constant.KEY.LOGIN_PHONE_NUMBER, "");
    }

    public static String getTokenId(Context context) {
        SharedPreferences sp = context.getSharedPreferences(KEY_USER_CONFIG, Context.MODE_PRIVATE);
        return sp.getString(KEY_TOKEN_ID, "");
    }

    public static void setTokenId(Context context, String tokenId) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY_USER_CONFIG, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_TOKEN_ID, tokenId);
        editor.commit();
    }

    public static void setEventId(Context context, String eventId) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY_USER_CONFIG, Context.MODE_PRIVATE).edit();
        editor.putString(Constant.KEY.EVENT_ID, eventId);
        editor.commit();
    }

    public static String getEventId(Context context) {
        SharedPreferences sp = context.getSharedPreferences(KEY_USER_CONFIG, Context.MODE_PRIVATE);
        return sp.getString(Constant.KEY.EVENT_ID, "");
    }
}
