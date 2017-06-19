package com.boco.whl.rxjavademo.utils;

import android.util.Log;

/**
 * 日志工具类，调试时设置为开启状态，发布时设置为关闭状态。
 * 
 * @author Marsh
 * @date 2013-12-12
 * @since Version 1.0
 */
public class LogUtil {

	public static boolean isDebug = true;
	static String Tag = "SHome";

	public static void v(String msg) {
		if (isDebug)
			Log.v(Tag, msg);
	}

	public static void v(String msg, Throwable t) {
		if (isDebug)
			Log.v(Tag, msg, t);
	}

	public static void d(String msg) {
		if (isDebug)
			Log.d(Tag, msg);
	}

	public static void d(String msg, Throwable t) {
		if (isDebug)
			Log.d(Tag, msg, t);
	}

	public static void i(String msg) {
		if (isDebug)
			Log.i(Tag, msg);
	}

	public static void i(String msg, Throwable t) {
		if (isDebug)
			Log.i(Tag, msg, t);
	}

	public static void w(String msg) {
		if (isDebug)
			Log.w(Tag, msg);
	}

	public static void w(String msg, Throwable t) {
		if (isDebug)
			Log.w(Tag, msg, t);
	}

	public static void e(String msg) {
		if (isDebug)
			Log.e(Tag, msg);
	}

	public static void e(String msg, Throwable t) {
		if (isDebug)
			Log.e(Tag, msg, t);
	}

	public static void print(String string, String string2) {
		// TODO Auto-generated method stub
		if (isDebug)
			Log.e(string, string2);
	}

}
